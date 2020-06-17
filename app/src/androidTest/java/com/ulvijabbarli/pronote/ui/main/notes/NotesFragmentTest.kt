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
import com.ulvijabbarli.pronote.FakeNoteRepository
import com.ulvijabbarli.pronote.R
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class NotesFragmentTest {

    private lateinit var repository: NoteRepository

    @Before
    fun initRepository(){
        repository = FakeNoteRepository()
    }

    @After
    fun cleanUpDb(){
        repository.deleteAllNote()
    }

    @Test
    fun displayNotes_whenRepositoryHasData(){

        repository.saveNote(Note(title = "TITLE1",description = "DESCRIPTION1")).blockingAwait()

        launchActivity()

        onView(withText("TITLE1")).check(matches(isDisplayed()))


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