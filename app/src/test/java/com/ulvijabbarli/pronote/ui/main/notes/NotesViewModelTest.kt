package com.ulvijabbarli.pronote.ui.main.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ulvijabbarli.pronote.RxSchedulerRule
import com.ulvijabbarli.pronote.data.FakeNoteRepository
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.getOrAwaitValue
import com.ulvijabbarli.pronote.util.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesViewModelTest {

    private val note1 = Note(1, "Title1", "Description1")
    private val note2 = Note(2, "Title2", "Description2")
    private val note3 = Note(3, "Title3", "Description3")

    // Subject under test
    private lateinit var notesViewModel: NotesViewModel

    private lateinit var notesRepository: FakeNoteRepository

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialize the repository with 3 notes
        notesRepository = FakeNoteRepository()

        notesRepository.saveNote(note1)
        notesRepository.saveNote(note2)
        notesRepository.saveNote(note3)

        notesViewModel = NotesViewModel(notesRepository)
    }

    @Test
    fun loadAllNotes_success() {
        // When load all notes
        notesViewModel.loadNoteList()

        // then verify that the data is loaded
        Truth
            .assertThat(notesViewModel.notes.getOrAwaitValue())
            .isEqualTo(Resource.Success(listOf(note1, note2, note3)))
    }

    @Test
    fun loadAllNotes_error() {
        // when load all notes with error case
        notesRepository.returnError = true
        notesViewModel.loadNoteList()


        // then verify that error is returned
        Truth
            .assertThat(notesViewModel.notes.getOrAwaitValue())
            .isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun clearAllNotesFromRepository() {
        // When clear all notes
        notesViewModel.clearAllNotes()

        // then the result should be true
        Truth
            .assertThat(notesViewModel.clearAllNotes.getOrAwaitValue())
            .isEqualTo(Resource.Success(true))
    }

    @Test
    fun openNotesDetail() {
        // When open a note detail
        notesViewModel.openNote(Event(note1))

        // then openNoteDetail event is triggered
        Truth
            .assertThat(notesViewModel.openNoteDetail.getOrAwaitValue())
            .isNotNull()
    }

}