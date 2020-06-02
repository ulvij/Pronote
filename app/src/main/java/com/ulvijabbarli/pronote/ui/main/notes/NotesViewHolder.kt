package com.ulvijabbarli.pronote.ui.main.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note_list.*

class NotesViewHolder(override val containerView: View,var glide:RequestManager) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {


    fun bind(note: Note) {
        glide.load(R.drawable.ic_image_note).into(image_note)
        text_note_title.text = note.title
    }

}