package com.ulvijabbarli.pronote.di.main

import com.ulvijabbarli.pronote.ui.main.add_edit_note.AddEditNoteFragment
import com.ulvijabbarli.pronote.ui.main.notes.NotesFragment
import com.ulvijabbarli.pronote.ui.main.notes.NotesModul
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules = [NotesModul::class])
    abstract fun provideNotesFragmentFactory(): NotesFragment

    @ContributesAndroidInjector
    abstract fun provideAddNoteFragmentFactory(): AddEditNoteFragment


}