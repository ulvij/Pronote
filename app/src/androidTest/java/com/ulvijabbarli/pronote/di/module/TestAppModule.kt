package com.ulvijabbarli.pronote.di.module

import com.ulvijabbarli.pronote.FakeNoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule() {

    @Provides
    @Singleton
    fun provideRepository(): FakeNoteRepository {
        return FakeNoteRepository()
    }

}