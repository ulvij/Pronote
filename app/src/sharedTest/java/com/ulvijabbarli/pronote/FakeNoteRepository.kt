package com.ulvijabbarli.pronote

import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

class FakeNoteRepository : NoteRepository {

    private val lock = Any()
    private var noteServiceData: LinkedHashMap<Long, Note> = LinkedHashMap()
    var observable = Observable.fromArray(noteServiceData.values)
    var returnError: Boolean = false

    override fun getAllNote(): Flowable<MutableList<Note>> {
        if (returnError) {
            return Flowable.error(Exception("Something went wrong"))
        }
        return Flowable.just(noteServiceData.values.toMutableList())
    }

    override fun getNote(id: Long): Flowable<Note> {
        if (returnError) {
            return Flowable.error(Exception("Could not find note"))
        }

        val note = noteServiceData[id]

        return if (note != null)
            Flowable.just(note)
        else
            Flowable.error(Exception("Could not find note"))
    }

    override fun saveNote(note: Note): Completable {
        if (returnError) {
            return Completable.error(Exception("Something went wrong"))
        }

        return try {
            noteServiceData[note.id] = note
            refreshObservable()
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    override fun deleteNote(id: Long): Completable {
        return try {
            noteServiceData.remove(id)
            refreshObservable()
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    override fun deleteAllNote(): Completable {
        if (returnError) {
            return Completable.error(Exception("Could not delete all notes"))
        }

        return try {
            noteServiceData.clear()
            refreshObservable()
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    private fun refreshObservable() {
        observable
            .mergeWith(Observable.just(noteServiceData.values))
            .distinct()
    }

    fun resetRepository() {
        synchronized(lock) {
            returnError = false
            deleteAllNote()
        }
    }

}