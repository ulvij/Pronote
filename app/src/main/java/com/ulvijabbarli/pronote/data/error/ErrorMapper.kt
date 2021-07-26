package com.ulvijabbarli.pronote.data.error

fun interface ErrorMapper {
    fun mapError(e: Throwable): Throwable
}