package com.ulvijabbarli.pronote.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulvijabbarli.pronote.data.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    /**
     * Observes list of notes.
     *
     * @return all notes.
     */
    @Query("SELECT * FROM note_table ORDER BY createdDate")
    fun getAllNote(): Flow<MutableList<Note>>

    /**
     * Save a note in the database. If the note already exists, replace it.
     *
     * @param note the note to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note)

    /**
     * Observes a single note.
     *
     * @param noteId the note id.
     * @return the note with noteId.
     */
    @Query("SELECT * FROM NOTE_TABLE WHERE id = :noteId")
    fun getNote(noteId: Long): Flow<Note>

    /**
     * Delete a note by id.
     *
     * @return the number of notes deleted. This should always be 1.
     */
    @Query("DELETE FROM NOTE_TABLE WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Long)

    /**
     * Delete all notes.
     */
    @Query("DELETE FROM NOTE_TABLE")
    suspend fun deleteNotes()
}