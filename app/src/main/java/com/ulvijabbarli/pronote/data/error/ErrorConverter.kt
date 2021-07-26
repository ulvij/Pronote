package com.ulvijabbarli.pronote.data.error

fun interface ErrorConverter {
    fun convert(t: Throwable): Throwable
}