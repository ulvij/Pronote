package com.ulvijabbarli.pronote.ui.base

import android.os.Bundle
import android.util.Log
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {


    companion object {
        val TAG = BaseActivity::class.qualifiedName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "BaseActivity is created")
    }
}