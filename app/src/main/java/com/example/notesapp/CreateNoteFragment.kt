package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.entities.Notes
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : BaseFragment() {

    var currentDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val simpleDateVal = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = simpleDateVal.format(Date())

        tv_date_time.text = currentDate

        img_done.setOnClickListener{
            saveNote()
        }

        img_back.setOnClickListener{
            replaceFragment(HomeFragment.newInstance(), false)
        }
    }

    private fun saveNote(){
        if(et_note_title.text.isNullOrEmpty()){
            Toast.makeText(context, "Title Required", Toast.LENGTH_SHORT).show()
        }
        if(et_note_sub_title.text.isNullOrEmpty()){
            Toast.makeText(context, "Sub-title Required", Toast.LENGTH_SHORT).show()
        }
        if(et_note_description.text.isNullOrEmpty()){
            Toast.makeText(context, "Description Required", Toast.LENGTH_SHORT).show()
        }

        launch {
            val notes = Notes()
            notes.title = et_note_title.text.toString()
            notes.subTitle = et_note_sub_title.text.toString()
            notes.noteText = et_note_description.text.toString()
            notes.dateTime = currentDate
            context?.let {
                NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                et_note_description.setText("")
                et_note_sub_title.setText("")
                et_note_title.setText("")
            }
        }
    }

    fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (isTransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
        }

        fragmentTransition.replace(R.id.frame_layout, fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }
}