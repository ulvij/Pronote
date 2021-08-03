package com.ulvijabbarli.pronote.ui.main.add_edit_note

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.data.source.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private var repository: NoteRepository
) : ViewModel() {

    private var _noteUpdateEvent: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val noteUpdateEvent = _noteUpdateEvent as LiveData<Resource<Boolean>>

    private var _noteDeleteEvent = MutableLiveData<Resource<Boolean>>()
    val noteDeleteEvent = _noteDeleteEvent as LiveData<Resource<Boolean>>

    private var _note = MutableLiveData<Resource<Note>>()
    val note = _note as LiveData<Resource<Note>>

    var isNewNote: Boolean = false
    var currentNoteId: String? = null

    companion object {
        val TAG = AddEditNoteViewModel::class.qualifiedName
    }

    init {
        Log.d(TAG, "Add Edit Note view model started")
    }

    fun start(noteId: String?) {
        viewModelScope.launch {
            currentNoteId = noteId
            if (noteId == null) {
                // no need to retrieve note, it's a new note
                isNewNote = true
                return@launch
            }

            isNewNote = false
            _note.value = Resource.Loading
//            when (val response = repository.getNote(noteId.toLong())) {
//                is Resource.Success -> _note.value = Resource.Success(response.data)
//                is Resource.Error -> _note.value = Resource.Error(response.exception)
//            }
        }
    }

    fun saveNote(title: String, description: String?) {
        if (Note(title = title, description = description).isInvalid) {
            _note.value = Resource.Error(Exception("Note can not be empty"))
            return
        }

        if (isNewNote || currentNoteId == null) {
            insertNote(Note(title = title, description = description))
        } else {
            val note = Note(currentNoteId!!.toLong(), title, description)
            updateNote(note)
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            if (isNewNote || currentNoteId == null) {
                _noteDeleteEvent.value =
                    Resource.Error(RuntimeException("deleteNote() was called but note is new."))
            }
            _noteDeleteEvent.value = Resource.Loading
//            when (val response = repository.deleteNote(currentNoteId!!.toLong())) {
//                is Resource.Success -> _noteDeleteEvent.value = Resource.Success(true)
//                is Resource.Error -> _noteDeleteEvent.value = Resource.Error(response.exception)
//            }

        }

    }


    private fun insertNote(note: Note) {
        viewModelScope.launch {
            _note.value = Resource.Loading
//            when (val response = repository.saveNote(note)) {
//                is Resource.Success -> _noteUpdateEvent.value = Resource.Success(true)
//                is Resource.Error -> _noteUpdateEvent.value = Resource.Error(response.exception)
//            }
        }
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch {
            if (isNewNote || currentNoteId == null) {
                _noteUpdateEvent.value =
                    Resource.Error(RuntimeException("updateNote() was called but note is new."))
            }

            _note.value = Resource.Loading
//            when (val response = repository.saveNote(note)) {
//                is Resource.Success -> _noteUpdateEvent.value = Resource.Success(true)
//                is Resource.Error -> _noteUpdateEvent.value = Resource.Error(response.exception)
//            }
        }
    }

}