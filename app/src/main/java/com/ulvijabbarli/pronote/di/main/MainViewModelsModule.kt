package com.ulvijabbarli.pronote.di.main

import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.di.base.ViewModelKeyJava
import com.ulvijabbarli.pronote.ui.main.MainViewModel
import com.ulvijabbarli.pronote.ui.main.notes.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKeyJava(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKeyJava(NotesViewModel::class)
    internal abstract fun bindNotesViewModel(notesViewModel: NotesViewModel): ViewModel
}