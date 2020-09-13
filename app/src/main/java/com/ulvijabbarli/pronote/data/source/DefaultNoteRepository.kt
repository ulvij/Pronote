package com.ulvijabbarli.pronote.data.source

import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNoteRepository @Inject constructor(
    private val notesDataSource: NotesDataSource
) : NoteRepository {

    companion object {
        val TAG = DefaultNoteRepository::class.qualifiedName
    }

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return notesDataSource.getAllNote()
    }

    override fun getNote(id: Long): Flowable<Note> {
        return notesDataSource.getNote(id)
    }

    override fun saveNote(note: Note): Completable {
        println("LOG_DEBUG-->DEFAULT--$note")
        return notesDataSource.saveNote(note)
    }

    override fun deleteNote(id: Long): Completable {
        return notesDataSource.deleteNote(id)
    }

    override fun deleteAllNote(): Completable {
        return notesDataSource.deleteAllNote()
    }
}