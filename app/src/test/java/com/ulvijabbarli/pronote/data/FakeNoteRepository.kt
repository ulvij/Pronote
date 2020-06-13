package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeNoteRepository : NoteRepository {

    var noteServiceData: LinkedHashMap<Long, Note> = LinkedHashMap()

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return Flowable.just(noteServiceData.values.toMutableList())
    }

    override fun getNote(id: Long): Flowable<Note> {
        val note = noteServiceData[id]

        return if (note != null)
            Flowable.just(note)
        else
            Flowable.error(Exception("Could not find note"))
    }

    override fun saveNote(note: Note): Completable {
        return Completable.fromAction { noteServiceData[note.id] = note }
    }

    override fun deleteNote(id: Long): Completable {
        return Completable.fromAction { noteServiceData.remove(id) }
    }

    override fun deleteAllNote(): Completable {
        return Completable.fromAction { noteServiceData.clear() }
    }

}