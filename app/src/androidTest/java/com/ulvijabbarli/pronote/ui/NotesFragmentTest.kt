package com.ulvijabbarli.pronote.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.ui.main.MainActivity
import com.ulvijabbarli.pronote.util.RecyclerViewItemCountAssertion
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class NotesFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var notesRepository: NoteRepository

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
        hiltRule.inject()
    }

    @After
    fun clear() {
        notesRepository.deleteAllNote().blockingAwait()
    }


    @Test
    fun displayNotesWhenRepositoryHasData() {
        notesRepository
            .saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
            .blockingAwait()

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))

    }

    @Test
    fun displayAllNotes() {
        notesRepository
            .saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
            .blockingAwait()
        notesRepository
            .saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))
            .blockingAwait()

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))
        onView(withText("TITLE2")).check(matches(isDisplayed()))

    }

    @Test
    fun displayEmptyViewWhenNoValues() {
        launch(MainActivity::class.java).use {
            onView(withId(R.id.linear_empty)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            onView(withId(R.id.image_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            onView(withId(R.id.text_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            onView(withId(R.id.recycler_view_notes)).check(RecyclerViewItemCountAssertion(0))
        }

    }

    @Test
    fun acceptToClearAllNotes_success() {
        notesRepository
            .saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
            .blockingAwait()
        notesRepository
            .saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))
            .blockingAwait()

        launchActivity()

        // simulate clear all notes action
        onView(withId(R.id.image_clear_all)).perform(click())
        onView(withText("Yes")).perform(click())

        // verify that all notes are cleared
        onView(withId(R.id.linear_empty)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.image_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.text_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.recycler_view_notes)).check(RecyclerViewItemCountAssertion(0))

    }

    @Test
    fun cancelToClearAllNotes() {
        notesRepository
            .saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
            .blockingAwait()
        notesRepository
            .saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))
            .blockingAwait()

        launchActivity()

        // simulate clear all notes action
        onView(withId(R.id.image_clear_all)).perform(click())
        onView(withText("No")).perform(click())

        // verify that all notes aren't cleared
        onView(withText("TITLE1")).check(matches(isDisplayed()))
        onView(withText("TITLE2")).check(matches(isDisplayed()))

    }

}