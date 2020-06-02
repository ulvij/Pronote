package com.ulvijabbarli.pronote.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ulvijabbarli.pronote.data.Note

@Database(entities = [Note::class], version = PronoteDatabase.DATABASE_VERSION, exportSchema = false)
abstract class PronoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "pronote_database"
        const val DATABASE_VERSION = 1
    }
}