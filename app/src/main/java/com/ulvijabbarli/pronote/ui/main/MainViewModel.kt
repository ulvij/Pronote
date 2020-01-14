package com.ulvijabbarli.pronote.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.model.Note
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private var posts: MediatorLiveData<MainResource<List<Note>>>? = null

    companion object {
        val TAG = MainViewModel::class.qualifiedName
    }

    init {
        Log.e(
            TAG,
            "Main view model is working"
        )
    }


//    fun observeNotes(): LiveData<MainResource<List<Note>>> {
//
//    }

}