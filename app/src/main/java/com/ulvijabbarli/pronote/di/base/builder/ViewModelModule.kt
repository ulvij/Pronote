package com.ulvijabbarli.pronote.di.base.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulvijabbarli.pronote.di.base.ViewModelKey
import com.ulvijabbarli.pronote.ui.main.add_edit_note.AddEditNoteViewModel
import com.ulvijabbarli.pronote.ui.main.notes.NotesViewModel
import com.ulvijabbarli.pronote.util.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    internal abstract fun bindNotesViewModel(notesViewModel: NotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEditNoteViewModel::class)
    internal abstract fun bindAddNoteViewModel(addEditNoteViewModel: AddEditNoteViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}