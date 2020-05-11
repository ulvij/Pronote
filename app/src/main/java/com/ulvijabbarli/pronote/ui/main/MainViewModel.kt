package com.ulvijabbarli.pronote.ui.main

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.DataHelper
import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.disposables.CompositeDisposable
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
}