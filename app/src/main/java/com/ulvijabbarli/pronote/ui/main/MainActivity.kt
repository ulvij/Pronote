package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ulvijabbarli.pronote.BaseActivity
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var mainViewModel:MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this,providerFactory).get(MainViewModel::class.java)
    }
}
