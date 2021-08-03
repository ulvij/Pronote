package com.ulvijabbarli.pronote.ui.main.notes.usecase

import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.error.ErrorConverter
import com.ulvijabbarli.pronote.data.source.DefaultNoteRepository
import com.ulvijabbarli.pronote.ui.base.BaseFlowUseCase
import com.ulvijabbarli.pronote.ui.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class ObserveNotesUseCase(
    context: CoroutineContext,
    converter: ErrorConverter,
    private val repository: DefaultNoteRepository
) : BaseFlowUseCase<Unit, MutableList<Note>>(context, converter) {

    override fun createFlow(params: Unit): Flow<MutableList<Note>> = repository.getAllNote()
}