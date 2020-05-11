package com.ulvijabbarli.pronote.ui.main.notes

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.DataHelper
import com.ulvijabbarli.pronote.data.model.Note
import com.ulvijabbarli.pronote.ui.main.MainResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    var dataHelper: DataHelper
) : ViewModel() {
    var notes: MediatorLiveData<MainResource<List<Note>>> = MediatorLiveData()
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
            notes.value = MainResource.Loading()
            notesCompositeDisposable.add(dataHelper.getAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { noteList ->
                        notes.value = MainResource.Success(noteList ?: emptyList())
                    },
                    { error ->
                        notes.value = MainResource.Error(error.message ?: "Something went wrong!")
                    }
                ))
        }
    }

}