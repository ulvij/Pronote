package com.ulvijabbarli.pronote.di.base.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulvijabbarli.pronote.di.base.ViewModelKeyJava
import com.ulvijabbarli.pronote.ui.main.MainViewModel
import com.ulvijabbarli.pronote.ui.main.notes.NotesViewModel
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKeyJava(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKeyJava(NotesViewModel::class)
    internal abstract fun bindNotesViewModel(notesViewModel: NotesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}