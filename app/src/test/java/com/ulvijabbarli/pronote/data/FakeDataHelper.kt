package com.ulvijabbarli.pronote.data

import io.reactivex.Completable
import io.reactivex.Flowable

class FakeDataHelper: DataHelper{


    override fun setAccessToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun getAccessToken(): String? {
        TODO("Not yet implemented")
    }

    override fun getAllNote(): Flowable<MutableList<Note>> {
        TODO("Not yet implemented")
    }

    override fun getNote(id: Long): Flowable<Note> {
        TODO("Not yet implemented")
    }

    override fun insertNote(note: Note): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteNote(id: Long): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteAllNote(): Completable {
        TODO("Not yet implemented")
    }

}