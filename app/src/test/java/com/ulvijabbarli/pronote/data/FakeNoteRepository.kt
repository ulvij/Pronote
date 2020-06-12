package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeNoteRepository:NoteRepository {

    var noteServiceData:LinkedHashMap<Long,Note> = LinkedHashMap()


    private val observablesNotes = Flowable.generate<MutableList<Note>>{}

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return observablesNotes
    }

    override fun getNote(id: Long): Flowable<Note> {
        noteServiceData[id]?.let {
            return Flowable.just(it)
        }
        throw Exception("Could not find note")
    }

    override fun saveNote(note: Note): Completable {
        noteServiceData[note.id] = note
        return Completable.complete()
    }

    override fun deleteNote(id: Long): Completable {
        noteServiceData.remove(id)
        return Completable.complete()
    }

    override fun deleteAllNote(): Completable {
        noteServiceData.clear()
        return Completable.complete()
    }


    fun refreshObservable(){

    }

}