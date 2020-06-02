package com.ulvijabbarli.pronote.data.source

import android.content.Context
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNoteRepository @Inject constructor(
    var notesDataSource: NotesDataSource,
    var context: Context
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

    override fun insertNote(note: Note): Completable {
        return notesDataSource.insertNote(note)
    }

    override fun deleteNote(id: Long): Completable {
        return notesDataSource.deleteNote(id)
    }

    override fun deleteAllNote(): Completable {
        return notesDataSource.deleteAllNote()
    }
}