package com.ulvijabbarli.pronote.ui.splash

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        val TAG = SplashViewModel::class.qualifiedName
    }

    init {
        Log.e(TAG, "Splash view model is working")
    }

    fun startTiming(openApp: () -> Unit) {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                openApp()
            }
        }.start()
    }
}