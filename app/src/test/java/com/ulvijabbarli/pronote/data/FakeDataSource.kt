package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeDataSource(var notes: MutableList<Note> = mutableListOf()) : NotesDataSource {

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return Flowable.just(notes)
    }

    override fun getNote(id: Long): Flowable<Note> {

        val note = notes.find { it.id == id }

        return if (note == null)
            Flowable.error(Exception("Could not find the note"))
        else Flowable.just(note)
    }

    override fun saveNote(note: Note): Completable {
        return notes.let {
            it.add(note)
            Completable.complete()
        }
    }

    override fun deleteNote(id: Long): Completable {
        val note = notes.find { it.id == id }
        return if (note == null)
            Completable.error(Exception("Could not find this note"))
        else {
            Completable.fromAction {
                notes.remove(note)
            }
        }
    }

    override fun deleteAllNote(): Completable {
        return Completable.fromAction {
            notes.clear()
        }
    }


}