package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesHelper
import com.ulvijabbarli.pronote.ui.base.BaseActivity
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelProviderFactory:ViewModelProviderFactory

    @Inject
    lateinit var prefHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment)
        mainViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(MainViewModel::class.java)
    }

}
