package com.ulvijabbarli.pronote.ui.main.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.util.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(var repository: NoteRepository) : ViewModel() {

    private var _notes = MutableLiveData<Resource<List<Note>>>()
    val notes = _notes as LiveData<Resource<List<Note>>>

    private var _clearAllNotes = MutableLiveData<Resource<Boolean>>()
    val clearAllNotes = _clearAllNotes as LiveData<Resource<Boolean>>

    private var _openNoteDetail = MutableLiveData<Event<Note>>()
    val openNoteDetail = _openNoteDetail as LiveData<Event<Note>>

    private var notesCompositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        val TAG = NotesViewModel::class.qualifiedName
    }

    init {
        Log.e(TAG, "Notes view model started")
    }

    override fun onCleared() {
        super.onCleared()
        notesCompositeDisposable.dispose()
    }


    fun loadNoteList() {
        if (_notes.value == null) {
            _notes.value = Resource.Loading
            notesCompositeDisposable.add(repository.getAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { noteList ->
                        _notes.value = Resource.Success(noteList ?: mutableListOf())
                    },
                    { error ->
                        _notes.value = Resource.Error(error as Exception)
                    }
                ))
        }
    }

    fun clearAllNotes() {
        _clearAllNotes.value = Resource.Loading
        notesCompositeDisposable.add(
            repository.deleteAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _clearAllNotes.value = Resource.Success(true)
                    },
                    { error ->
                        _clearAllNotes.value = Resource.Error(error as Exception)
                    }
                )
        )
    }

    fun openNote(event: Event<Note>) {
        _openNoteDetail.value = event
    }

}