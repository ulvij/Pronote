package com.ulvijabbarli.pronote.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY createdDate")
    fun getAllNote(): Flowable<MutableList<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note): Completable

    @Query("SELECT * FROM NOTE_TABLE WHERE id = :id")
    fun getNote(id: Long): Flowable<Note>

    @Query("DELETE FROM NOTE_TABLE WHERE id = :id")
    fun deleteNote(id: Long): Completable

    @Query("DELETE FROM NOTE_TABLE")
    fun deleteAll(): Completable
}