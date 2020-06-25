package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeDataSource(private var notes: MutableList<Note> = mutableListOf()) : NotesDataSource {

    var observable = Flowable.fromArray(notes)

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return observable
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
            refreshObservable()
            Completable.complete()
        }
    }

    override fun deleteNote(id: Long): Completable {
        val note = notes.find { it.id == id }
        return if (note == null)
            Completable.error(Exception("Could not find this note"))
        else {
            notes.remove(note)
            refreshObservable()
            Completable.complete()
        }
    }

    override fun deleteAllNote(): Completable {
        notes.clear()
        refreshObservable()
        return Completable.complete()
    }

    private fun refreshObservable() {
        observable = Flowable.fromArray(notes)
    }


}