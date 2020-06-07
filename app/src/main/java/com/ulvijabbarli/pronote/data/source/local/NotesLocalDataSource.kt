package com.ulvijabbarli.pronote.data.source.local

import com.ulvijabbarli.pronote.data.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesLocalDataSource @Inject constructor(var pronoteDatabase: PronoteDatabase) :
    NotesDataSource {

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return pronoteDatabase.noteDao().getAllNote()
    }

    override fun getNote(id: Long): Flowable<Note> {
        return pronoteDatabase.noteDao().getNote(id)
    }

    override fun saveNote(note: Note): Completable {
        return pronoteDatabase.noteDao().saveNote(note)
    }

    override fun deleteNote(id: Long): Completable {
        return pronoteDatabase.noteDao().deleteNoteById(id)
    }

    override fun deleteAllNote(): Completable {
        return pronoteDatabase.noteDao().deleteNotes()
    }

}