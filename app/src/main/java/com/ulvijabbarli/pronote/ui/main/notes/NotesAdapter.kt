package com.ulvijabbarli.pronote.ui.main.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.model.Note

class NotesAdapter(var glide:RequestManager) : RecyclerView.Adapter<NotesViewHolder>() {

    private val noteList = mutableListOf<Note>()
    var clickListener: ((note: Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note_list, parent, false)
        return NotesViewHolder(view,glide)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(noteList[position],clickListener)
    }

    fun updateDataSet(data: List<Note>) {
        noteList.clear()
        noteList.addAll(data)
        notifyDataSetChanged()
    }

}