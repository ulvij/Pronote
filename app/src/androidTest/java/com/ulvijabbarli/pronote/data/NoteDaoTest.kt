package com.ulvijabbarli.pronote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ulvijabbarli.pronote.data.source.local.NoteDao
import com.ulvijabbarli.pronote.data.source.local.PronoteDatabase
import io.reactivex.functions.Predicate
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test the implementation of {@link NoteDao}
 */
@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var database: PronoteDatabase
    private lateinit var noteDao: NoteDao
    private val note = Note(id = 1, title = "NOTE_TITLE", description = "NOTE_DESCRIPTION")

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PronoteDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        noteDao = database.noteDao()
    }


    @Test
    fun getNotesWhenNoNoteSaved() {
        // Get no values when subscribe empty db
        noteDao
            .getAllNote()
            .test()
            .assertValue { it.isEmpty() }
    }

    @Test
    fun insertAndGetNoteById() {
        // Given that we have a note in the data source
        noteDao.saveNote(note).blockingAwait()

        // When subscribing to the emissions of note
        noteDao.getNote(note.id)
            .test()
            .assertValue(Predicate {
                return@Predicate it.id == note.id
            })
    }

    @Test
    fun updateAndGetNote() {
        // Given that we have a note in the data source
        noteDao.saveNote(note).blockingAwait()

        // When we are updating the title of the note
        val updatedNote = Note(note.id, "NEW_NOTE_TITLE", note.description)
        noteDao.saveNote(updatedNote).blockingAwait()

        // When subscribing to the emissions of the note
        noteDao.getNote(note.id)
            .test()
            .assertValue(Predicate {
                return@Predicate note.id == it.id && it.title?.equals("NEW_NOTE_TITLE") ?: false
            })
    }


    @Test
    fun deleteAllNotesAndGetNoValues() {
        // Given that we have a note in the data source
        noteDao.saveNote(note).blockingAwait()

        // When we are deleting all notes
        noteDao.deleteNotes().blockingAwait()

        // When we are subscribing to the emissions of the notes
        noteDao.getAllNote()
            .test()
            .assertValue { it.isEmpty() }
    }

    @Test
    fun deleteNoteByIdAndGetNoValues() {
        // Given that we have a note in the data source
        noteDao.saveNote(note).blockingAwait()

        // When we are deleting note by id
        noteDao.deleteNoteById(note.id).blockingAwait()

        // Get no values when we are subscribing to the notes
        noteDao.getAllNote()
            .test()
            .assertValue(Predicate {
                return@Predicate it.isEmpty()
            })

    }

    @After
    fun closeDb() {
        database.close()
    }
}