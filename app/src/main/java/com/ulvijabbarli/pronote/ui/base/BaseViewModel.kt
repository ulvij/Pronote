package com.ulvijabbarli.pronote.ui.base

import android.os.Bundle
import androidx.lifecycle.*
import com.ulvijabbarli.pronote.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.UnknownError

open class BaseViewModel<State, Effect> : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _state = MutableLiveData<State>()

    private val _effect = SingleLiveEvent<Effect>()

    private val _commonEffect = SingleLiveEvent<BaseEffect>()
    val commonEffect: LiveData<BaseEffect>
        get() = _commonEffect

    val state: LiveData<State>
        get() = _state

    val effect: SingleLiveEvent<Effect>
        get() = _effect

    protected fun postState(state: State) {
        _state.value = state
    }

    protected fun postEffect(effect: Effect) {
        _effect.postValue(effect)
    }

    private var arguments: Bundle? = null

    fun setArguments(data: Bundle?) {
        arguments = data
    }

    fun getArguments(): Bundle? {
        return arguments
    }

    fun launchAll(
        loadingHandle: (Boolean) -> Unit = ::handleLoading,
        errorHandle: (Throwable) -> Unit = ::handleError,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            try {
                loadingHandle(true)
                block()
            } catch (t: Throwable) {
                errorHandle(t)
            } finally {
                loadingHandle(false)
            }
        }
    }

    protected fun handleError(t: Throwable) {
        return when (t) {
            else -> _commonEffect.postValue(UnknownError(cause = t))
        }
    }

    protected fun <T> Flow<T>.handleError(): Flow<T> = catch { handleError(it) }

    protected fun <T> Flow<T>.handleLoading(loadingHandle: (Boolean) -> Unit = ::handleLoading): Flow<T> =
        flow {
            this@handleLoading
                .onStart { loadingHandle(true) }
                .onCompletion { loadingHandle(false) }
                .collect { value ->
                    loadingHandle(false)
                    emit(value)
                }
        }

    protected fun <T> Flow<T>.launch(
        scope: CoroutineScope = viewModelScope,
        loadingHandle: (Boolean) -> Unit = ::handleLoading
    ): Job =
        this.handleError()
            .handleLoading(loadingHandle)
            .launchIn(scope)

    protected fun <T> Flow<T>.launchNoLoading(scope: CoroutineScope = viewModelScope): Job =
        this.handleError()
            .launchIn(scope)

    protected fun <P, R, U : BaseUseCase<P, R>> U.launch(
        param: P,
        loadingHandle: (Boolean) -> Unit = ::handleLoading,
        block: CompletionBlock<R> = {}
    ) {
        viewModelScope.launch {
            val actualRequest = BaseUseCase.Request<R>().apply(block)

            val proxy: CompletionBlock<R> = {
                onStart = {
                    loadingHandle(true)
                    actualRequest.onStart?.invoke()
                }
                onSuccess = {
                    actualRequest.onSuccess(it)
                }
                onCancel = {
                    actualRequest.onCancel?.invoke(it)
                }
                onTerminate = {
                    loadingHandle(false)
                    actualRequest.onTerminate
                }
                onError = {
                    Timber.e(it)
                    actualRequest.onError?.invoke(it) ?: handleError(it)
                }
            }
            execute(param, proxy)
        }
    }

    protected fun <P, R, U : BaseUseCase<P, R>> U.launchNoLoading(
        param: P,
        block: CompletionBlock<R> = {}
    ) {
        viewModelScope.launch {
            val actualRequest = BaseUseCase.Request<R>().apply(block)

            val proxy: CompletionBlock<R> = {
                onStart = actualRequest.onStart
                onSuccess = actualRequest.onSuccess
                onCancel = actualRequest.onCancel
                onTerminate = actualRequest.onTerminate
                onError = {
                    Timber.e(it)
                    actualRequest.onError ?: handleError(it)
                }
            }
            execute(param, proxy)
        }
    }

    protected fun <P, R, U : BaseUseCase<P, R>> U.launchUnsafe(
        param: P,
        block: CompletionBlock<R> = {}
    ) {
        viewModelScope.launch { execute(param, block) }
    }

    protected fun handleLoading(state: Boolean) {
        _isLoading.postValue(state)
    }

}