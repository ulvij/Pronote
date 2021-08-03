package com.ulvijabbarli.pronote.di

import com.ulvijabbarli.pronote.data.error.ErrorConverter
import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.ui.main.notes.usecase.DeleteAllNoteUseCase
import com.ulvijabbarli.pronote.ui.main.notes.usecase.ObserveNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun getObserveNotesUserCase(
        context: CoroutineContext,
        converter: ErrorConverter,
        repository: DefaultNoteRepository
    ): ObserveNotesUseCase {
        return ObserveNotesUseCase(context, converter, repository)
    }

    @Singleton
    @Provides
    fun getDeleteAllNotesUserCase(
        context: CoroutineContext,
        converter: ErrorConverter,
        repository: DefaultNoteRepository
    ): DeleteAllNoteUseCase {
        return DeleteAllNoteUseCase(context, converter, repository)
    }
}