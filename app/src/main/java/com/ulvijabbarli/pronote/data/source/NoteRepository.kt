package com.ulvijabbarli.pronote.data.source

import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow


/**
 * Interface to the data layer.
 */
interface NoteRepository {

    fun getAllNote(): Flow<MutableList<Note>>

    fun getNote(id: Long): Flow<Note>

    suspend fun saveNote(note: Note)

    suspend fun deleteNote(id: Long)

    suspend fun deleteAllNote()

}