package com.ulvijabbarli.pronote.ui.main.add_note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.ui.main.MainResource
import com.ulvijabbarli.pronote.util.hideKeyboard
import com.ulvijabbarli.pronote.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_note.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory
    lateinit var navController: NavController
    lateinit var addNoteViewModel: AddNoteViewModel

    companion object {
        val TAG: String = AddNoteFragment::class.java.name
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
        addNoteViewModel = ViewModelProviders
            .of(this, viewModelProvider)
            .get(AddNoteViewModel::class.java)
        initListeners()
        bindObservers()
    }

    private fun initListeners() {
        image_back.setOnClickListener { navController.popBackStack() }
        image_save.setOnClickListener {
            hideKeyboard()
            addNoteViewModel.insertNote(
                text_title.text.toString(),
                text_content.text.toString()
            )
        }
    }

    private fun bindObservers() {
        addNoteViewModel.liveNote
            .observe(viewLifecycleOwner, Observer { noteResource ->
                if (noteResource != null) {
                    when (noteResource) {
                        is MainResource.Loading -> {
                            controlLoading(true)
                            Log.d(TAG, "onChanged: LOADING...")
                        }
                        is MainResource.Error -> {
                            controlLoading(false)
                            showError(noteResource.message)
                            Log.d(TAG, "onChanged: ERROR... ${noteResource.message}")
                        }
                        is MainResource.Success -> {
                            controlLoading(false)
                            handleSuccess()
                            Log.d(TAG, "onChanged: SUCCESS...")
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
        Toast.makeText(requireContext(), getString(R.string.message_note_created), Toast.LENGTH_SHORT).show()
        navController.popBackStack()
    }


}
