package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesHelper
import com.ulvijabbarli.pronote.ui.base.BaseActivity
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var prefHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment)
        mainViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.addNoteFragment) {
                float_add_note.hide()
            } else {
                float_add_note.show()
            }
        }

        float_add_note.setOnClickListener {
            navController.navigate(R.id.action_notesFragment_to_addNoteFragment)
        }
    }
}
