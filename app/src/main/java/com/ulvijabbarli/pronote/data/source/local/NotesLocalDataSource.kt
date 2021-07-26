package com.ulvijabbarli.pronote.data.source.local

import com.ulvijabbarli.pronote.data.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesLocalDataSource @Inject constructor(var noteDao: NoteDao) :
    NotesDataSource {

    override fun getAllNote(): Flow<MutableList<Note>> {
        return noteDao.getAllNote()
    }

    override fun getNote(id: Long): Flow<Note> {
        return noteDao.getNote(id)
    }

    override suspend fun saveNote(note: Note) {
        noteDao.saveNote(note)
    }

    override suspend fun deleteNote(id: Long) {
        noteDao.deleteNoteById(id)
    }

    override suspend fun deleteAllNote() {
        noteDao.deleteNotes()
    }

}