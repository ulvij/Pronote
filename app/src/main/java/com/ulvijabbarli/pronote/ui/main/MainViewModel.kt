package com.ulvijabbarli.pronote.ui.main

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.DataHelper
import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var dataHelper: DataHelper
) : ViewModel() {

    var notes: MediatorLiveData<MainResource<List<Note>>> = MediatorLiveData()
    var notesCompositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        val TAG = MainViewModel::class.qualifiedName
    }

    init {
        Log.e(
            TAG,
            "Main view model is working"
        )
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