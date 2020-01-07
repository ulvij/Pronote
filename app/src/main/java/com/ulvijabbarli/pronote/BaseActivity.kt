package com.ulvijabbarli.pronote

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    companion object {
        val TAG = BaseActivity::class.qualifiedName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "BaseActivity is created")
    }
}