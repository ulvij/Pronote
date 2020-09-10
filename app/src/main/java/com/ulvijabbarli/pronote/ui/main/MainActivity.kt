package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulvijabbarli.pronote.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
