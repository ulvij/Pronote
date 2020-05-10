package com.ulvijabbarli.pronote.ui.main.add_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ulvijabbarli.pronote.R
import dagger.android.support.DaggerFragment

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

}
