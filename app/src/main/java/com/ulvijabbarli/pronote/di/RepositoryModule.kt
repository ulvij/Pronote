package com.ulvijabbarli.pronote.di

import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.data.source.NoteRepository
import com.ulvijabbarli.pronote.data.source.local.NotesDataSource
import com.ulvijabbarli.pronote.data.source.local.NotesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideIOContext(): CoroutineContext {
        return Dispatchers.IO
    }

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