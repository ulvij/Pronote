package com.ulvijabbarli.pronote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ulvijabbarli.pronote.data.dao.NoteDao
import com.ulvijabbarli.pronote.data.model.Note

@Database(entities = [Note::class], version = AppDatabase.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "note_database"
        const val DATABASE_VERSION = 1
    }
}