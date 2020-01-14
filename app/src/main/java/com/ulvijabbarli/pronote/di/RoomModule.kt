package com.ulvijabbarli.pronote.di

import android.content.Context
import androidx.room.Room
import com.ulvijabbarli.pronote.data.dao.NoteDao
import com.ulvijabbarli.pronote.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun getNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

}