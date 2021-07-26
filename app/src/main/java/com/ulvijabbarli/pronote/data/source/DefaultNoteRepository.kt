package com.ulvijabbarli.pronote.data.source

import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNoteRepository @Inject constructor(
    private val notesDataSource: NotesDataSource
) : NoteRepository {

    override fun getAllNote(): Flow<MutableList<Note>> {
        return notesDataSource.getAllNote()
    }

    override fun getNote(id: Long): Flow<Note> {
        return notesDataSource.getNote(id)
    }

    override suspend fun saveNote(note: Note) {
        notesDataSource.saveNote(note)
    }

    override suspend fun deleteNote(id: Long) {
        notesDataSource.deleteNote(id)
    }

    override suspend fun deleteAllNote() {
        notesDataSource.deleteAllNote()
    }
}