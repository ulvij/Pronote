package com.ulvijabbarli.pronote.ui.base

import com.ulvijabbarli.pronote.data.error.ErrorConverter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = BaseUseCase.Request<T>.() -> Unit

abstract class BaseUseCase<P, R>(
    private val executionContext: CoroutineContext,
    private val errorConverter: ErrorConverter,
) {
    protected abstract suspend fun executeOnBackground(params: P): R

    suspend fun execute(params: P, block: CompletionBlock<R> = {}) {
        val request = Request<R>().apply(block).also { it.onStart?.invoke() }
        try {
            val result = withContext(executionContext) { executeOnBackground(params) }
            request.onSuccess(result)
        } catch (e: CancellationException) {
            request.onCancel?.invoke(e)
        } catch (e: Throwable) {
            request.onError?.invoke(errorConverter.convert(e))
        } finally {
            request.onTerminate?.invoke()
        }
    }

    class Request<T> {
        var onSuccess: (T) -> Unit = {}
        var onStart: (() -> Unit)? = null
        var onError: ((Throwable) -> Unit)? = null
        var onCancel: ((CancellationException) -> Unit)? = null
        var onTerminate: (() -> Unit)? = null
    }
}

abstract class BaseFlowUseCase<P, R>(
    private val executionContext: CoroutineContext,
    private val errorMapper: ErrorConverter
) {
    protected abstract fun createFlow(params: P): Flow<R>

    fun execute(params: P): Flow<R> =
        createFlow(params)
            .flowOn(executionContext)
            .catch { throw errorMapper.convert(it) }
}