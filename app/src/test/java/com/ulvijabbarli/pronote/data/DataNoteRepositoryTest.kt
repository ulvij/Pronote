package com.ulvijabbarli.pronote.data

import com.google.common.truth.Truth.assertThat
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
            notesRepository.saveNote(it).blockingAwait()
        }

        // Then notes load all notes from local data source
        notesRepository.getAllNote()
            .test()
            .assertValue {
                return@assertValue it == allNotes
            }
    }

    @Test
    fun getNotesEmptyRepositoryAndUninitializedCache() {
        // When create empty data resource
        val emptySource = FakeDataSource()
        val noteRepository = DefaultNoteRepository(emptySource)

        // Then we are subscribing no values
        assertThat(noteRepository.getAllNote().isEmpty)
    }

    @Test
    fun saveAndSubscribeNewNote() {
        // When create and save new note
        val note = newNotes[0]
        notesRepository.saveNote(note)

        // Then subscribe this new note
        notesRepository.getNote(note.id)
            .test()
            .assertValue { n ->
                return@assertValue n.id == note.id && n.title == note.title && n.description == note.description
            }
    }

    @Test
    fun deleteAllNotes() {
        // Get initial data and verify that notes is not empty
        notesRepository.getAllNote().test().assertValue { it.isNotEmpty() }

        // Delete all notes
        notesRepository.deleteAllNote().blockingAwait()

        // Get data after deletion verify notes are empty now
        notesRepository.getAllNote().test().assertValue { it.isEmpty() }

    }

    @Test
    fun saveAndDeleteSingleNote(){
        // When save new note
        val note = newNotes[0]
        notesRepository.saveNote(note)

        // Delete last saved note
        notesRepository.deleteNote(note.id).blockingAwait()

        // Verify that last note deleted
        notesRepository.getNote(note.id)
            .test()
            .assertNoValues()
    }
}