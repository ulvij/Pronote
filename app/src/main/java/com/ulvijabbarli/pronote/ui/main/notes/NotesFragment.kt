package com.ulvijabbarli.pronote.ui.main.notes

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.util.Constants
import com.ulvijabbarli.pronote.util.EventObserver
import com.ulvijabbarli.pronote.util.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notes.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class NotesFragment : DaggerFragment() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var navController: NavController
    private lateinit var notesAdapter: NotesAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var glideManager: RequestManager

    companion object {
        val TAG = NotesFragment::class.java.name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        notesViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(NotesViewModel::class.java)
        image_clear_all.setOnClickListener { showAreYouSureToClearAllDialog() }
        setUpNotesAdapter()
        setUpObservers()
        notesViewModel.loadNoteList()
    }

    private fun setUpNotesAdapter() {
        notesAdapter = NotesAdapter(glideManager, notesViewModel)
        recycler_view_notes.adapter = notesAdapter
    }

    private fun setUpObservers() {
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

        notesViewModel.notes.observe(viewLifecycleOwner,
            Observer { noteResource ->
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

    private fun showAreYouSureToClearAllDialog() {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.title_warning))
            .setMessage(getString(R.string.message_are_you_sure_to_clear_all_notes))
            .setPositiveButton(getString(R.string.action_yes)) { dialog, _ ->
                dialog.dismiss()
                clearAllNotes()
            }
            .setNegativeButton(getString(R.string.action_no)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun clearAllNotes() {
        notesViewModel.clearAllNotes.observe(viewLifecycleOwner,
            Observer { clearNotesResponse ->
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
        notesViewModel.clearAllNotes()
    }
}
