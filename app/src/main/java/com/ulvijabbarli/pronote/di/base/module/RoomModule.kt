package com.ulvijabbarli.pronote.di.base.module

import android.content.Context
import androidx.room.Room
import com.ulvijabbarli.pronote.data.source.local.NoteDao
import com.ulvijabbarli.pronote.data.source.local.PronoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): PronoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PronoteDatabase::class.java,
            PronoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getNoteDao(pronoteDatabase: PronoteDatabase): NoteDao {
        return pronoteDatabase.noteDao()
    }

}