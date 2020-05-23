package com.ulvijabbarli.pronote.ui.main.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ulvijabbarli.pronote.data.DataHelper
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesViewModelTest {

    // Subject under test
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var dataHelper: DataHelper

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

    }


}