package com.ulvijabbarli.pronote.ui.main.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.util.Constants
import com.ulvijabbarli.pronote.util.EventObserver
import com.ulvijabbarli.pronote.util.showAlert
import com.ulvijabbarli.pronote.util.viewmodel.ViewModelProviderFactory
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_notes.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val notesViewModel: NotesViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var notesAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpClickListeners()
        setUpNotesAdapter()
        setUpObservers()
        notesViewModel.loadNoteList()
    }

    private fun setUpClickListeners() {
        image_clear_all.setOnClickListener {
            view?.showAlert(
                getString(R.string.title_warning),
                getString(R.string.message_are_you_sure_to_clear_all_notes),
                { notesViewModel.clearAllNotes() })
        }
        float_add_note.setOnClickListener {
            navController.navigate(
                R.id.action_notesFragment_to_addEditNoteFragment,
                bundleOf(Pair(Constants.title, getString(R.string.title_add_note)))
            )
        }
    }

    private fun setUpNotesAdapter() {
        notesAdapter = NotesAdapter { notesViewModel.openNote(it) }
        recycler_view_notes.adapter = notesAdapter
    }

    private fun setUpObservers() {
        // Open Note observer
        notesViewModel.openNoteDetail.observe(viewLifecycleOwner,
            EventObserver {
                navController.navigate(
                    R.id.action_notesFragment_to_addEditNoteFragment,
                    bundleOf(
                        Pair(Constants.title, getString(R.string.title_edit_note)),
                        Pair(Constants.noteId, it.id.toString())
                    )
                )
            })

        // note list observer
        notesViewModel.notes.observe(viewLifecycleOwner,
            { noteResource ->
                if (noteResource != null) {
                    when (noteResource) {
                        is Resource.Loading -> {
                            configureLoading(true)
                        }
                        is Resource.Error -> {
                            configureLoading(false)
                            showError(noteResource.exception.message)
                        }
                        is Resource.Success -> {
                            configureLoading(false)
                            handleData(noteResource.data)
                        }
                    }
                }
            })

        // clear all notes observer
        notesViewModel.clearAllNotes.observe(viewLifecycleOwner,
            { clearNotesResponse ->
                if (clearNotesResponse != null) {
                    when (clearNotesResponse) {
                        is Resource.Loading -> {
                            configureLoading(true)
                        }
                        is Resource.Error -> {
                            showError(clearNotesResponse.exception.message)
                            configureLoading(false)
                        }
                        is Resource.Success -> configureLoading(false)
                    }
                }
            }
        )
    }

    private fun configureLoading(show: Boolean) {
        progress_bar_notes.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(message: String?) {
        message?.let { error ->
            view?.let { v ->
                Snackbar.make(v, error, 2000).show()
            } ?: Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
    }

    private fun handleData(notes: List<Note>?) {
        notesAdapter.updateDataSet(notes ?: emptyList())
        if (notesAdapter.itemCount == 0) {
            recycler_view_notes.visibility = View.INVISIBLE
            linear_empty.visibility = View.VISIBLE
        } else {
            recycler_view_notes.visibility = View.VISIBLE
            linear_empty.visibility = View.INVISIBLE
        }
    }

}
