package com.ulvijabbarli.pronote.di.module

import android.content.Context
import androidx.room.Room
import com.ulvijabbarli.pronote.data.source.local.NoteDao
import com.ulvijabbarli.pronote.data.source.local.PronoteDatabase
import com.ulvijabbarli.pronote.di.base.module.RoomModule

class TestRoomModule : RoomModule() {

    override fun getDatabase(context: Context): PronoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            PronoteDatabase::class.java
        ).fallbackToDestructiveMigration().build()
    }

    override fun getNoteDao(pronoteDatabase: PronoteDatabase): NoteDao {
        return pronoteDatabase.noteDao()
    }

}