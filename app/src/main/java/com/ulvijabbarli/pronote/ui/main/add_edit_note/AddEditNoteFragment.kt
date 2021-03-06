package com.ulvijabbarli.pronote.ui.main.add_edit_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.util.Constants
import com.ulvijabbarli.pronote.util.hideKeyboard
import com.ulvijabbarli.pronote.util.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class AddEditNoteFragment : Fragment(R.layout.fragment_add_note) {

    private val addEditNoteViewModel: AddEditNoteViewModel by viewModels()
    private lateinit var navController: NavController

    companion object {
        val TAG: String = AddEditNoteFragment::class.java.name
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)

        setUpBaseData()
        setUpListeners()
        setUpObservers()
        addEditNoteViewModel.start(arguments?.getString(Constants.noteId))
    }

    private fun setUpBaseData() {
        arguments?.let { args ->
            text_title.text = args.getString(Constants.title)
            args.getString(Constants.noteId)
                ?.let { image_delete.visibility = View.VISIBLE }
                ?: kotlin.run { image_delete.visibility = View.INVISIBLE }
        }
    }

    private fun setUpListeners() {
        image_back.setOnClickListener { navController.popBackStack() }
        float_save_note.setOnClickListener {
            hideKeyboard()
            addEditNoteViewModel.saveNote(
                text_note_title.text.trim().toString(),
                text_note.text.trim().toString()
            )
        }

        image_delete.setOnClickListener {
            view?.showAlert(
                getString(R.string.title_warning),
                getString(R.string.message_are_you_sure_to_delete_this_note),
                { addEditNoteViewModel.deleteNote() })
        }
    }

    private fun setUpObservers() {
        addEditNoteViewModel.note
            .observe(viewLifecycleOwner, Observer { noteResource ->
                if (noteResource != null) {
                    when (noteResource) {
                        is Resource.Loading -> {
                            controlLoading(true)
                        }
                        is Resource.Error -> {
                            controlLoading(false)
                            showError(noteResource.exception.message)
                        }
                        is Resource.Success -> {
                            controlLoading(false)
                            text_note_title.setText(noteResource.data.title)
                            text_note.setText(noteResource.data.description)
                        }
                    }
                }
            })

        addEditNoteViewModel.noteUpdateEvent.observe(viewLifecycleOwner, Observer { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Success -> {
                        controlLoading(false)
                        navController.popBackStack()
                    }
                    is Resource.Error -> {
                        controlLoading(false)
                        showError(resource.exception.message)
                    }
                    Resource.Loading -> controlLoading(true)
                }
            }
        })

        addEditNoteViewModel.noteDeleteEvent.observe(
            viewLifecycleOwner,
            { deleteNoteResource ->
                if (deleteNoteResource != null) {
                    when (deleteNoteResource) {
                        is Resource.Success -> {
                            controlLoading(false)
                            navController.popBackStack()
                        }
                        is Resource.Error -> {
                            controlLoading(false)
                            showError(deleteNoteResource.exception.message)
                        }
                        Resource.Loading -> controlLoading(true)
                    }
                }
            })
    }


    private fun controlLoading(show: Boolean) {
        progressbar.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(message: String?) {
        message?.let { error ->
            view?.let { v ->
                Snackbar.make(v, error, 3000).show()
            } ?: Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
    }
}
