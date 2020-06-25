package com.ulvijabbarli.pronote.ui.main.add_edit_note

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ulvijabbarli.pronote.FakeNoteRepository
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.TestApplication
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class AddEditNoteFragmentTest {

    private lateinit var notesRepository: NoteRepository

    private fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            (activity.findViewById(R.id.recycler_view_notes) as RecyclerView).itemAnimator = null
        }
        return activityScenario
    }

    @Before
    fun setUp() {
        notesRepository = TestApplication.appComponent().noteRepository()
    }

    @After
    fun clear() {
        (notesRepository as FakeNoteRepository).resetRepository()
    }

    @Test
    fun createNewNote_success() {
        // check that there are no any notes
        notesRepository.getAllNote()
            .test()
            .assertValue {
                return@assertValue it.size == 0
            }

        launchActivity()

        // open add note fragment and type not details
        onView(withId(R.id.float_add_note)).perform(click())
        onView(withId(R.id.text_note_title)).perform(typeText("New Note Title"))
        onView(withId(R.id.text_note)).perform(typeText("New Note"))

        onView(withId(R.id.float_save_note)).perform(click())

        // verify that note is created
        notesRepository.getAllNote()
            .test()
            .assertValue {
                return@assertValue it.size == 1
            }
    }

    @Test
    fun createNewNote_error() {
        launchActivity()

        // open add note fragment and type not details
        onView(withId(R.id.float_add_note)).perform(click())
        onView(withId(R.id.text_note_title)).perform(typeText("New Note Title"))
        onView(withId(R.id.text_note)).perform(typeText("New Note"))

        // set flat for return error when save note
        (notesRepository as FakeNoteRepository).returnError = true

        onView(withId(R.id.float_save_note)).perform(click())

        // verify that note is not created
        notesRepository.getAllNote()
            .test()
            ?.assertNoValues()

        // verify that Snackbar displayed
        onView(withText("Something went wrong")).check(matches(isDisplayed()))
    }

    @Test
    fun createEmptyNoteFields() {
        launchActivity()

        // open add note fragment and type not details
        onView(withId(R.id.float_add_note)).perform(click())

        onView(withId(R.id.float_save_note)).perform(click())

        // verify that Snackbar displayed
        onView(withText("Note can not be empty")).check(matches(isDisplayed()))

    }

    @Test
    fun checkDeleteButtonVisibility_CreateNoteCase() {
        launchActivity()

        // open add note fragment
        onView(withId(R.id.float_add_note)).perform(click())

        // verify that delete button is invisible
        onView(withId(R.id.image_delete)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))

    }

    @Test
    fun checkDeleteButtonVisibility_EditNoteCase() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE", description = "DESC1"))

        launchActivity()

        // open note details page
        onView(withText("TITLE")).perform(click())

        // verify that delete button is visible
        onView(withId(R.id.image_delete)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    @Test
    fun acceptToDeleteNote() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE", description = "DESC1"))

        launchActivity()

        // open note details page
        onView(withText("TITLE")).perform(click())


        // click to delete note
        onView(withId(R.id.image_delete)).perform(click())
        onView(withText("Yes")).perform(click())

        // verify that the note is deleted
        notesRepository.getNote(1)
            .test()
            .assertNoValues()

    }

    @Test
    fun cancelToDeleteNote() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE", description = "DESC"))

        launchActivity()

        // open note details page
        onView(withText("TITLE")).perform(click())


        // click to delete note
        onView(withId(R.id.image_delete)).perform(click())
        onView(withText("No")).perform(click())

        // verify that the note is deleted
        notesRepository.getNote(1)
            .test()
            .assertValue {
                return@assertValue (it.title == "TITLE" && it.description == "DESC")
            }

    }

}