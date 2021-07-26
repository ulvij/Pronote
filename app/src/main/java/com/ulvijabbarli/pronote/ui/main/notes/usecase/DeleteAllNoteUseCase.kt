package com.ulvijabbarli.pronote.ui.main.notes.usecase

import com.ulvijabbarli.pronote.data.error.ErrorConverter
import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.ui.base.BaseUseCase
import kotlin.coroutines.CoroutineContext

class DeleteAllNoteUseCase(
    coroutineContext: CoroutineContext,
    errorConverter: ErrorConverter,
    val repository: DefaultNoteRepository
) :
    BaseUseCase<Unit, Unit>(coroutineContext, errorConverter) {
    override suspend fun executeOnBackground(params: Unit) {
        repository.deleteAllNote()
    }
}