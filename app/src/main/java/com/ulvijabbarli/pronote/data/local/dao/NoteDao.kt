package com.ulvijabbarli.pronote.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulvijabbarli.pronote.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY createdDate")
    fun getAllNote():LiveData<MutableList<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM NOTE_TABLE WHERE id = :id")
    fun getNote(id:Long):LiveData<Note>

    @Query("DELETE FROM NOTE_TABLE WHERE id = :id")
    fun deleteNote(id: Long)

    @Query("DELETE FROM NOTE_TABLE")
    suspend fun deleteAll()
}