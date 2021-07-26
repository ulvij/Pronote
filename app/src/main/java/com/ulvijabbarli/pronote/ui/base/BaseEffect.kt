package com.ulvijabbarli.pronote.ui.base

import androidx.annotation.StringRes

interface BaseEffect

abstract class BaseUiError : BaseEffect
class UnknownError(val cause: Throwable) : BaseUiError()
class MessageError(@StringRes val messageId: Int) : BaseUiError()