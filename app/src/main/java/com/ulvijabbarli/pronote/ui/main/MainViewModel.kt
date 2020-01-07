package com.ulvijabbarli.pronote.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

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