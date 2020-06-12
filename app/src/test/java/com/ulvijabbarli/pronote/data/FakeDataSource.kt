package com.ulvijabbarli.pronote.data

import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

class FakeDataSource: NotesDataSource{


    override fun getAllNote(): Flowable<MutableList<Note>> {
        TODO("Not yet implemented")
    }

    override fun getNote(id: Long): Flowable<Note> {
        TODO("Not yet implemented")
    }

    override fun saveNote(note: Note): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteNote(id: Long): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteAllNote(): Completable {
        TODO("Not yet implemented")
    }


}