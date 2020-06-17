package com.ulvijabbarli.pronote

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import com.ulvijabbarli.pronote.data.source.local.NotesLocalDataSource
import com.ulvijabbarli.pronote.data.source.local.PronoteDatabase

object ServiceLocator {

//    private val lock = Any()
//    private val database: PronoteDatabase? = null
//
//    @Volatile
//    var noteRepository: NoteRepository? = null
//        @VisibleForTesting set
//
//    fun provideNoteRepository(context: Context): NoteRepository {
//        synchronized(this) {
//            return noteRepository ?: noteRepository ?: createNoteRepository(context)
//        }
//    }
//
//    private fun createNoteRepository(context: Context): NoteRepository {
//        val newRepo = DefaultNoteRepository(createNoteDataSource(context))
//        noteRepository = newRepo
//        return newRepo
//    }
//
//    private fun createNoteDataSource(context: Context): NotesDataSource {
//        val database = database ?: createDatabase(context)
//        return NotesLocalDataSource(database.noteDao())
//    }
//
//    private fun createDatabase(context: Context): PronoteDatabase {
//        //return
//    }


}