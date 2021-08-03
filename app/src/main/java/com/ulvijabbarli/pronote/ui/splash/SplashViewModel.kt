package com.ulvijabbarli.pronote.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        val TAG = SplashViewModel::class.qualifiedName
    }

    init {
        Timber.e(TAG, "Splash view model is working")
    }

    fun startTiming(openApp: () -> Unit) {
        viewModelScope.launch {
            delay(1000)
            openApp()
        }
    }
}