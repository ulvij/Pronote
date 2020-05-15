package com.ulvijabbarli.pronote.data.local.db

import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.Completable
import io.reactivex.Flowable

interface DbHelper {

    fun getAllNote(): Flowable<MutableList<Note>>

    fun getNote(id: Long): Flowable<Note>

    fun insertNote(note: Note): Completable

    fun deleteNote(id: Long): Completable

    fun deleteAllNote(): Completable

}