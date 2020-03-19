package com.ulvijabbarli.pronote.di.base.module

import android.content.Context
import androidx.room.Room
import com.ulvijabbarli.pronote.data.local.dao.NoteDao
import com.ulvijabbarli.pronote.data.local.db.AppDatabase
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
            ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

}