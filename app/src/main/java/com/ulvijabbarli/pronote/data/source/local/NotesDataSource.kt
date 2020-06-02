package com.ulvijabbarli.pronote.data.source.local

import com.ulvijabbarli.pronote.data.Note
import io.reactivex.Completable
import io.reactivex.Flowable

interface NotesDataSource {

    fun getAllNote(): Flowable<MutableList<Note>>

    fun getNote(id: Long): Flowable<Note>

    fun insertNote(note: Note): Completable

    fun deleteNote(id: Long): Completable

    fun deleteAllNote(): Completable

}