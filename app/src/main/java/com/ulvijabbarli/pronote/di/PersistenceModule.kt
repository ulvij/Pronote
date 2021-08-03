package com.ulvijabbarli.pronote.di

import android.app.Application
import androidx.room.Room
import com.ulvijabbarli.pronote.data.source.local.NoteDao
import com.ulvijabbarli.pronote.data.source.local.PronoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Singleton
    @Provides
    fun getDatabase(application: Application): PronoteDatabase {
        return Room
            .databaseBuilder(
                application,
                PronoteDatabase::class.java,
                PronoteDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getNoteDao(pronoteDatabase: PronoteDatabase): NoteDao {
        return pronoteDatabase.noteDao()
    }
}