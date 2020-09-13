package com.ulvijabbarli.pronote.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ulvijabbarli.pronote.FakeNoteRepository
import com.ulvijabbarli.pronote.RxSchedulerRule
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.getOrAwaitValue
import com.ulvijabbarli.pronote.ui.main.add_edit_note.AddEditNoteViewModel
import io.reactivex.functions.Predicate
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddEditNoteViewModelTest {

    private val note = Note(1, "Title", "Description")

    // Subject under test
    private lateinit var addEditNoteViewModel: AddEditNoteViewModel

    private lateinit var notesRepository: FakeNoteRepository

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        notesRepository = FakeNoteRepository()
        notesRepository.saveNote(note)

        addEditNoteViewModel = AddEditNoteViewModel(notesRepository)
    }


    @Test
    fun startForEditingNote_success() {
        // start with note id saved before
        addEditNoteViewModel.start(note.id.toString())

        // Verify related fields according to editing note case
        Truth.assertThat(addEditNoteViewModel.currentNoteId).isEqualTo(note.id.toString())
        Truth.assertThat(addEditNoteViewModel.isNewNote).isFalse()
        Truth
            .assertThat(addEditNoteViewModel.note.getOrAwaitValue())
            .isEqualTo(Resource.Success(note))
    }

    @Test
    fun startForEditingNote_error() {
        notesRepository.returnError = true

        // start with note id saved before
        addEditNoteViewModel.start(note.id.toString())

        // Verify related fields according to editing note case
        Truth.assertThat(addEditNoteViewModel.currentNoteId).isEqualTo(note.id.toString())
        Truth.assertThat(addEditNoteViewModel.isNewNote).isFalse()
        Truth
            .assertThat(addEditNoteViewModel.note.getOrAwaitValue())
            .isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun startForCreatingNote() {
        // start with null note id for creating note
        addEditNoteViewModel.start(null)

        // Verify related fields according to creating note case
        Truth.assertThat(addEditNoteViewModel.currentNoteId).isNull()
        Truth.assertThat(addEditNoteViewModel.isNewNote).isTrue()
        Truth.assertThat(addEditNoteViewModel.note.value).isNull()
    }

    @Test
    fun createOrEditNote_inValidFields() {
        // when create note with invalid fields
        addEditNoteViewModel.saveNote("", "")

        // Then result should be exception
        Truth
            .assertThat(addEditNoteViewModel.note.getOrAwaitValue())
            .isInstanceOf(Resource.Error::class.java)

    }

    @Test
    fun createNewNote_success() {
        // when create note
        addEditNoteViewModel.saveNote("New Title", "New Description")

        // Then result should be success
        Truth
            .assertThat(addEditNoteViewModel.noteUpdateEvent.getOrAwaitValue())
            .isEqualTo(Resource.Success(true))
    }

    @Test
    fun createNewNote_error() {
        // setting flag for returning error
        notesRepository.returnError = true

        // when create note
        addEditNoteViewModel.saveNote("New Title", "New Description")

        // Then result should be success
        Truth
            .assertThat(addEditNoteViewModel.noteUpdateEvent.getOrAwaitValue())
            .isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun updateExistNote(){

        // start with exist note
        addEditNoteViewModel.start(note.id.toString())

        // update fields of selected note
        addEditNoteViewModel.saveNote("Title New","Description New")

        // Verify completion of operation
        Truth.assertThat(addEditNoteViewModel.noteUpdateEvent.getOrAwaitValue())
            .isEqualTo(Resource.Success(true))

        // Compare result of editing
        notesRepository.getNote(note.id)
            .test()
            .assertValue(Predicate {
                return@Predicate it.id ==note.id && it.title.equals("Title New") && it.description.equals("Description New")
            })
    }

}