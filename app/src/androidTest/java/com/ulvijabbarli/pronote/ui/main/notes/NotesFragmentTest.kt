package com.ulvijabbarli.pronote.ui.main.notes

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
import com.ulvijabbarli.pronote.util.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


//TODO: Implement more instrumentation test cases that uses observation of data from repository
@RunWith(AndroidJUnit4::class)
@MediumTest
class NotesFragmentTest {

    private lateinit var notesRepository: NoteRepository

    private fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = launch(MainActivity::class.java)
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
    fun displayNotesWhenRepositoryHasData() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))

    }

    @Test
    fun displayAllNotes() {

        notesRepository.saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
            .blockingAwait()
        notesRepository.saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))
            .blockingAwait()

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))
        onView(withText("TITLE2")).check(matches(isDisplayed()))

    }

    @Test
    fun displayEmptyViewWhenNoValues() {

        launchActivity()

        onView(withId(R.id.linear_empty)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.image_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.text_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.recycler_view_notes)).check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    fun acceptToClearAllNotes_success() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
        notesRepository.saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))

        launchActivity()

        // simulate clear all notes action
        onView(withId(R.id.image_clear_all)).perform(click())
        onView(withText("Yes")).perform(click())

        // verify that all notes are cleared
        notesRepository.getAllNote()
            .test()
            .assertValue {
                return@assertValue it.isEmpty()
            }
    }

    @Test
    fun cancelToClearAllNotes() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
        notesRepository.saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))

        launchActivity()

        // simulate clear all notes action
        onView(withId(R.id.image_clear_all)).perform(click())
        onView(withText("No")).perform(click())

        // verify that all notes aren't cleared
        notesRepository.getAllNote()
            .test()
            .assertValue {
                return@assertValue it.size == 2
            }
    }

}