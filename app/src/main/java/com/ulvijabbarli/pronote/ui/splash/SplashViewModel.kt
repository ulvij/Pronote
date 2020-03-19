package com.ulvijabbarli.pronote.ui.splash

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.ui.main.MainViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        val TAG = MainViewModel::class.qualifiedName
    }

    init {
        Log.e(TAG, "Splash view model is working")
    }

    fun startTiming(openApp: () -> Unit) {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                openApp.invoke()
            }
        }.start()
    }
}