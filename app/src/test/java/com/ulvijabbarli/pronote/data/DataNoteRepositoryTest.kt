package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import org.junit.Before
import org.junit.Test

class DataNoteRepositoryTest {


    private val note1 = Note(1, "Title1", "Description1")
    private val note2 = Note(2, "Title2", "Description2")
    private val note3 = Note(3, "Title3", "Description3")
    private val allNotes = listOf(note1, note2, note3).sortedBy { it.id }
    private val localNotes = listOf(note1, note2).sortedBy { it.id }
    private val newNotes = listOf(note3).sortedBy { it.id }

    private lateinit var notesLocalDataSource: FakeDataSource


    // Class under test
    private lateinit var notesRepository: DefaultNoteRepository

    @Before
    fun createRepository() {
        notesLocalDataSource = FakeDataSource(localNotes.toMutableList())

        notesRepository = DefaultNoteRepository(notesLocalDataSource)
    }

    @Test
    fun saveNewNotesAndRequestAllNotes() {
        // When save new note to database
        newNotes.forEach {
            notesRepository.saveNote(it)
        }

        // Then notes load all notes from local data source
        notesRepository.getAllNote()
            .test()
            .assertValue {
                return@assertValue it == allNotes
            }
    }

}