package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeNoteRepository : NoteRepository {

    private var noteServiceData: LinkedHashMap<Long, Note> = LinkedHashMap()
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
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    override fun deleteNote(id: Long): Completable {
        return try {
            noteServiceData.remove(id)
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

    override fun deleteAllNote(): Completable {
        return try {
            noteServiceData.clear()
            Completable.complete()
        } catch (e: Exception) {
            Completable.error(e)
        }
    }

}