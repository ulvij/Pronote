package com.ulvijabbarli.pronote.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.util.Constants
import com.ulvijabbarli.pronote.util.hideKeyboard
import com.ulvijabbarli.pronote.util.viewmodel.ViewModelProviderFactory
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

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
            navController.navigate(
                R.id.action_notesFragment_to_addEditNoteFragment,
                bundleOf(Pair(Constants.title, getString(R.string.title_add_note)))
            )
        }
    }
}
