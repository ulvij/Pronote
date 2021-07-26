package com.ulvijabbarli.pronote.data.error

class ErrorConverterImpl(private val mappers: Set<ErrorMapper>) : ErrorConverter {
    override fun convert(t: Throwable): Throwable {
        mappers.forEach {
            val error = it.mapError(t)
            if (error is HandledException) return error
        }

        return t
    }
}