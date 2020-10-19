package com.ulvijabbarli.pronote.ui.main.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.FtsOptions
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.util.Event

class NotesAdapter(private val clickListener: (Event<Note>) -> Unit) :
    RecyclerView.Adapter<NotesViewHolder>() {

    private val noteList = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note_list, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.containerView.setOnClickListener {
            clickListener(Event(noteList[position]))
        }
        holder.bind(noteList[position])
    }

    fun updateDataSet(data: List<Note>) {
        noteList.clear()
        noteList.addAll(data)
        notifyDataSetChanged()
    }

}