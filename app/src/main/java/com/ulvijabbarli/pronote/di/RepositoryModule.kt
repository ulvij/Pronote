package com.ulvijabbarli.pronote.di

import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import com.ulvijabbarli.pronote.data.source.local.NotesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

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

}