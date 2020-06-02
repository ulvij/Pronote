package com.ulvijabbarli.pronote.ui.main.notes

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    var repository: NoteRepository
) : ViewModel() {
    var notes: MediatorLiveData<Resource<List<Note>>> = MediatorLiveData()
    var clearAllNotes:MediatorLiveData<Resource<Boolean>> = MediatorLiveData()
    var notesCompositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        val TAG = NotesViewModel::class.qualifiedName
    }

    init {
        Log.e(
            TAG,
            "Notes view model is working"
        )
    }

    override fun onCleared() {
        super.onCleared()
        notesCompositeDisposable.dispose()
    }


    fun loadNoteList() {
        if (notes.value == null) {
            notes.value = Resource.Loading
            notesCompositeDisposable.add(repository.getAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { noteList ->
                        notes.value = Resource.Success(noteList ?: mutableListOf())
                    },
                    { error ->
                        notes.value = Resource.Error(error as Exception)
                    }
                ))
        }
    }

    fun clearAllNotes() {
        clearAllNotes.value = Resource.Loading
        notesCompositeDisposable.add(
            repository.deleteAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                      clearAllNotes.value = Resource.Success(true)
                    },
                    { error ->
                        clearAllNotes.value = Resource.Error(error as Exception)
                    }
                )
        )
    }

}