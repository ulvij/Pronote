package com.ulvijabbarli.pronote.ui.main.notes

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.TestApplication
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.ui.main.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class NotesFragmentTest {

    private lateinit var notesRepository: NoteRepository

    @Before
    fun setUp() {
        notesRepository = TestApplication.appComponent().noteRepository()
    }


    @Test
    fun displayNotes_whenRepositoryHasData() {

        notesRepository.saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))

    }

    @Test
    fun showAllNotes() {

        notesRepository.saveNote(Note(id = 1, title = "TITLE1", description = "DESC1"))
        notesRepository.saveNote(Note(id = 2, title = "TITLE2", description = "DESC2"))

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))
        onView(withText("TITLE2")).check(matches(isDisplayed()))

    }


    private fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            // Disable animations in RecyclerView
            (activity.findViewById(R.id.recycler_view_notes) as RecyclerView).itemAnimator = null
        }
        return activityScenario
    }

}