package com.ulvijabbarli.pronote.data.local.db

import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.Flowable

interface DbHelper {

    fun getAllNote(): Flowable<MutableList<Note>>

    fun getNote(id: Long): Flowable<Note>

    fun insertNote(note: Note): Flowable<Boolean>

    fun deleteNote(id: Long): Flowable<Boolean>

    fun deleteAllNote(): Flowable<Boolean>

}