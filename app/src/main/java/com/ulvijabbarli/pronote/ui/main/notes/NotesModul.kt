package com.ulvijabbarli.pronote.ui.main.notes

import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class NotesModul {

    @Provides
    fun notesAdapter(glide:RequestManager): NotesAdapter {
        return NotesAdapter(glide)
    }

}