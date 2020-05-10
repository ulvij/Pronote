package com.ulvijabbarli.pronote.ui.main.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.ui.main.MainResource
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class NotesFragment : DaggerFragment() {

    lateinit var notesViewModel: NotesViewModel

    companion object {
        val TAG = NotesFragment::class.qualifiedName ?: "NotesFragment"
    }


    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(NotesViewModel::class.java)
        getNoteList()
    }

    private fun getNoteList() {
        notesViewModel.notes.observe(viewLifecycleOwner,
            Observer { noteResource ->
                if (noteResource != null) {
                    when (noteResource) {
                        is MainResource.Loading -> {
                            Log.d(TAG, "onChanged: LOADING...")
                        }
                        is MainResource.Error -> {
                            Log.d(TAG, "onChanged: ERROR... ${noteResource.message}")
                        }
                        is MainResource.Success -> {
                            Log.d(TAG, "onChanged: SUCCESS...")
                        }
                    }
                }
            })
        notesViewModel.loadNoteList()
    }

}
