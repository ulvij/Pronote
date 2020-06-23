package com.ulvijabbarli.pronote.ui.main.add_edit_note

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddEditNoteViewModel @Inject constructor(
    var repository: NoteRepository
) : ViewModel() {

    private var _noteUpdateEvent: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val noteUpdateEvent = _noteUpdateEvent as LiveData<Resource<Boolean>>

    private var _noteDeleteEvent = MutableLiveData<Resource<Boolean>>()
    val noteDeleteEvent = _noteDeleteEvent as LiveData<Resource<Boolean>>

    private var _note = MutableLiveData<Resource<Note>>()
    val note = _note as LiveData<Resource<Note>>

    var isNewNote: Boolean = false
    var currentNoteId: String? = null

    var disposables: CompositeDisposable = CompositeDisposable()

    companion object {
        val TAG = AddEditNoteViewModel::class.qualifiedName
    }

    init {
        Log.e(TAG, "Add Edit Note view model started")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


    fun start(noteId: String?) {
        this.currentNoteId = noteId
        if (noteId == null) {
            // no need to retrieve note, it's a new note
            isNewNote = true
            return
        }

        isNewNote = false
        _note.value = Resource.Loading
        disposables.add(
            repository.getNote(noteId.toLong())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _note.value = Resource.Success(it)
                    },
                    { error ->
                        _note.value = Resource.Error(error as Exception)
                    }
                )
        )
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
        if (isNewNote || currentNoteId == null) {
            _noteDeleteEvent.value =
                Resource.Error(RuntimeException("deleteNote() was called but note is new."))
        }

        _noteDeleteEvent.value = Resource.Loading

        disposables.add(repository.deleteNote(currentNoteId!!.toLong())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _noteDeleteEvent.value = Resource.Success(true)
                },
                {
                    _noteDeleteEvent.value = Resource.Error(it as Exception)
                }
            ))

    }


    private fun insertNote(note: Note) {
        _note.value = Resource.Loading

        disposables.add(repository.saveNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _noteUpdateEvent.value = Resource.Success(true)
                },
                { error ->
                    _noteUpdateEvent.value = Resource.Error(error as Exception)
                }
            )
        )
    }

    private fun updateNote(note: Note) {
        if (isNewNote || currentNoteId == null) {
            _noteUpdateEvent.value =
                Resource.Error(RuntimeException("updateNote() was called but note is new."))
        }

        _note.value = Resource.Loading

        disposables.add(repository.saveNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _noteUpdateEvent.value = Resource.Success(true)
                },
                { error ->
                    _noteUpdateEvent.value = Resource.Error(error as Exception)
                }
            )
        )

    }

}