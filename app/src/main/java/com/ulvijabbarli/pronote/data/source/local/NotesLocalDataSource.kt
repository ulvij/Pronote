package com.ulvijabbarli.pronote.data.source.local

import com.ulvijabbarli.pronote.data.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesLocalDataSource @Inject constructor(var noteDao: NoteDao) :
    NotesDataSource {

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return noteDao.getAllNote()
    }

    override fun getNote(id: Long): Flowable<Note> {
        return noteDao.getNote(id)
    }

    override fun saveNote(note: Note): Completable {
        return noteDao.saveNote(note)
    }

    override fun deleteNote(id: Long): Completable {
        return noteDao.deleteNoteById(id)
    }

    override fun deleteAllNote(): Completable {
        return noteDao.deleteNotes()
    }

}