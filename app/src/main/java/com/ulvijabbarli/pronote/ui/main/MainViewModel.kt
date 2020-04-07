package com.ulvijabbarli.pronote.ui.main

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.DataHelper
import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    var notes: MediatorLiveData<MainResource<List<Note>>>? = null

    @Inject
    lateinit var dataHelper: DataHelper

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
        if (notes == null) {
            notes = MediatorLiveData()
            notes?.value = MainResource.Loading()

            dataHelper.getAllNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { error ->
                    notes?.value = MainResource.Error(error.message ?: "Something went wrong!")
                }
                .subscribe { noteList ->
                    notes?.value = MainResource.Success(noteList ?: emptyList())
                }
        }
    }
}