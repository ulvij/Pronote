package com.ulvijabbarli.pronote.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNoteRepository @Inject constructor(
    var notesDataSource: NotesDataSource
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
        return notesDataSource.saveNote(note)
    }

    override fun deleteNote(id: Long): Completable {
        return notesDataSource.deleteNote(id)
    }

    override fun deleteAllNote(): Completable {
        return notesDataSource.deleteAllNote()
    }
}