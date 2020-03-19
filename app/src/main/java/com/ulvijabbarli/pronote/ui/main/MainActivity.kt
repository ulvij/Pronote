package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ulvijabbarli.pronote.ui.base.BaseActivity
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}
