package com.ulvijabbarli.pronote.di.base.module

import android.app.Application
import android.content.Context
import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import com.ulvijabbarli.pronote.data.source.local.NotesLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(defaultNoteRepository: DefaultNoteRepository): NoteRepository {
        return defaultNoteRepository
    }

    @Provides
    @Singleton
    fun provideDataSource(notesLocalDataSource: NotesLocalDataSource): NotesDataSource {
        return notesLocalDataSource
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}