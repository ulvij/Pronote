package com.ulvijabbarli.pronote.data.source.local

import com.ulvijabbarli.pronote.data.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface NotesDataSource {

    fun getAllNote(): Flow<MutableList<Note>>

    fun getNote(id: Long): Flow<Note>

    suspend fun saveNote(note: Note)

    suspend fun deleteNote(id: Long)

    suspend fun deleteAllNote()

}