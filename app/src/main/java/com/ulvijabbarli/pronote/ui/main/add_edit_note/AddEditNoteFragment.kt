package com.ulvijabbarli.pronote.ui.main.add_edit_note

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
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.util.hideKeyboard
import com.ulvijabbarli.pronote.util.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_note.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AddEditNoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory
    lateinit var navController: NavController
    lateinit var addEditNoteViewModel: AddEditNoteViewModel

    companion object {
        val TAG: String = AddEditNoteFragment::class.java.name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)
        addEditNoteViewModel = ViewModelProviders
            .of(this, viewModelProvider)
            .get(AddEditNoteViewModel::class.java)
        text_title.text = arguments?.getString("title")
        initListeners()
        bindObservers()
    }

    private fun initListeners() {
        image_back.setOnClickListener { navController.popBackStack() }
        image_save.setOnClickListener {
            hideKeyboard()
            addEditNoteViewModel.insertNote(
                text_title.text.toString(),
                text_note.text.toString()
            )
        }
    }

    private fun bindObservers() {
        addEditNoteViewModel.liveNote
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
                            handleSuccess()
                        }
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

    private fun handleSuccess() {
        Toast.makeText(
            requireContext(),
            getString(R.string.message_note_created),
            Toast.LENGTH_SHORT
        ).show()
        navController.popBackStack()
    }


}
