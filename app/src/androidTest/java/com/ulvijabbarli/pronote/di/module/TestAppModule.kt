package com.ulvijabbarli.pronote.di.module

import com.ulvijabbarli.pronote.FakeNoteRepository
import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.data.source.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule(private val fakeNoteRepository: FakeNoteRepository) {

    @Provides
    @Singleton
    fun provideRepository(defaultNoteRepository: DefaultNoteRepository): NoteRepository {
        return fakeNoteRepository
    }

}