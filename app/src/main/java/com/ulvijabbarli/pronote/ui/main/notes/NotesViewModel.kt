package com.ulvijabbarli.pronote.ui.main.notes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.ui.base.BaseViewModel
import com.ulvijabbarli.pronote.ui.main.notes.usecase.DeleteAllNoteUseCase
import com.ulvijabbarli.pronote.ui.main.notes.usecase.ObserveNotesUseCase
import com.ulvijabbarli.pronote.util.Event
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

sealed class NotesState {
    class ShowNotes(list: MutableList<Note>) : NotesState()
}

sealed class NotesEffect {
    object DeletedAllNotes : NotesEffect()
}

class NotesViewModel @ViewModelInject constructor(
    val observeNotesUseCase: ObserveNotesUseCase,
    val deleteAllNoteUseCase: DeleteAllNoteUseCase
) :
    BaseViewModel<NotesState, NotesEffect>() {

    private var _clearAllNotes = MutableLiveData<Resource<Boolean>>()
    val clearAllNotes = _clearAllNotes as LiveData<Resource<Boolean>>

    private var _openNoteDetail = MutableLiveData<Event<Note>>()
    val openNoteDetail = _openNoteDetail as LiveData<Event<Note>>

    init {
        observeNotesUseCase.execute(Unit).filterNotNull()
            .onEach { postState(NotesState.ShowNotes(it)) }
            .launchNoLoading()
    }

    fun clearAllNotes() {
        deleteAllNoteUseCase.launch(Unit) {
            onSuccess = { postEffect(NotesEffect.DeletedAllNotes) }
        }
    }

    fun openNote(event: Event<Note>) {
        _openNoteDetail.value = event
    }

}