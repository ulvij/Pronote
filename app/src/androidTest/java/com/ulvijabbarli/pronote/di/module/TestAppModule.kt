package com.ulvijabbarli.pronote.di.module

import com.ulvijabbarli.pronote.FakeNoteRepository
import com.ulvijabbarli.pronote.data.source.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideRepository(): NoteRepository {
        return FakeNoteRepository()
    }

}