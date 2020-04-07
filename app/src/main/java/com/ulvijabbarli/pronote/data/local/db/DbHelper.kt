package com.ulvijabbarli.pronote.data.local.db

import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.rxjava3.core.Observable

interface DbHelper {

    fun getAllNote(): Observable<MutableList<Note>>

    fun getNote(id: Long): Observable<Note>

    fun insertNote(note: Note): Observable<Boolean>

    fun deleteNote(id: Long): Observable<Boolean>

    fun deleteAllNote(): Observable<Boolean>

}