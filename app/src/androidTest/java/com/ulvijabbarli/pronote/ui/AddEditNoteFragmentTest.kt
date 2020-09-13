package com.ulvijabbarli.pronote.ui

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
class AddEditNoteFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var notesRepository: NoteRepository

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
        hiltRule.inject()
    }

    @After
    fun clear() {
        notesRepository.deleteAllNote().blockingAwait()
    }

    @Test
    fun createNewNote_success() {
        launchActivity()

        // open add note fragment and type not details
        onView(withId(R.id.float_add_note)).perform(click())
        onView(withId(R.id.text_note_title)).perform(typeText("New Note Title"))
        onView(withId(R.id.text_note)).perform(typeText("New Note"))

        onView(withId(R.id.float_save_note)).perform(click())

        // verify that note is created
        onView(withText("New Note Title")).check(matches(isDisplayed()))
        onView(withText("New Note")).check(matches(isDisplayed()))

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
        notesRepository.saveNote(Note(id = 1, title = "TITLE", description = "DESC1")).blockingAwait()

        launchActivity()

        // open note details page
        onView(withText("TITLE")).perform(click())

        // verify that delete button is visible
        onView(withId(R.id.image_delete)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    @Test
    fun acceptToDeleteNote() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE", description = "DESC1")).blockingAwait()

        launchActivity()

        // open note details page
        onView(withText("TITLE")).perform(click())

        // click to delete note
        onView(withId(R.id.image_delete)).perform(click())
        onView(withText("Yes")).perform(click())

        // verify that the note is deleted
        onView(withId(R.id.linear_empty)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.image_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.text_empty_note)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.recycler_view_notes)).check(RecyclerViewItemCountAssertion(0))

    }

    @Test
    fun cancelToDeleteNote() {
        notesRepository.saveNote(Note(id = 1, title = "TITLE", description = "DESC")).blockingAwait()

        launchActivity()

        // open note details page
        onView(withText("TITLE")).perform(click())

        // click to delete note
        onView(withId(R.id.image_delete)).perform(click())
        onView(withText("No")).perform(click())

        // verify that the note is not deleted
        onView(withText("TITLE")).check(matches(isDisplayed()))
        onView(withText("DESC")).check(matches(isDisplayed()))

    }

}