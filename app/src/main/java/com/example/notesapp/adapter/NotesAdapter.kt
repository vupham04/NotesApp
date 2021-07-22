package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    var arrList = ArrayList<Notes>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.tv_title.text = arrList[position].title
        holder.itemView.tv_desc.text = arrList[position].noteText
        holder.itemView.tv_date_time.text = arrList[position].dateTime
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    fun setData(arrNotesList: List<Notes>){
        arrList = arrNotesList as ArrayList<Notes>
    }

    class NotesViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

}