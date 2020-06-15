package com.ulvijabbarli.pronote.ui.main.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ulvijabbarli.pronote.data.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note_list.*

class NotesViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(note: Note) {
        text_note_title.text = note.title
        text_note_content.text = note.description
    }

}