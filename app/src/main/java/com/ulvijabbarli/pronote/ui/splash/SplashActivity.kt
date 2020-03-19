package com.ulvijabbarli.pronote.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        splashViewModel.startTiming { startActivity(Intent(this, MainActivity::class.java)) }
    }
}
