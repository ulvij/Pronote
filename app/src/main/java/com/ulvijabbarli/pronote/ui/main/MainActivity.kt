package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesHelper
import com.ulvijabbarli.pronote.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var prefHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getNoteList()
    }

    private fun getNoteList() {
        mainViewModel.notes?.observe(this,
            Observer { noteResource->
                if (noteResource!=null){
                    when(noteResource){
                        is MainResource.Loading->{
                            Log.d(
                                "LOG_NOTE_LIST",
                                "onChanged: LOADING..."
                            )
                        }
                        is MainResource.Error->{
                            Log.d(
                                "LOG_NOTE_LIST",
                                "onChanged: ERROR... ${noteResource.message}"
                            )
                        }
                        is MainResource.Success->{
                            Log.d(
                                "LOG_NOTE_LIST",
                                "onChanged: SUCCESS..."
                            )
                        }
                    }
                }
            })
        mainViewModel.loadNoteList()
    }


}
