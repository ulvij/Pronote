package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.ui.BaseActivity
import com.ulvijabbarli.pronote.util.hideKeyboard
import com.ulvijabbarli.pronote.util.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            hideKeyboard()
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
