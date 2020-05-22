package com.ulvijabbarli.pronote.util

import android.content.Context

class ClassUnderTest(var context: Context) {

    init {
        context.getSharedPreferences("TEST", Context.MODE_PRIVATE).edit()
            .putString("hello_world", "HELLO_WORLD").apply()
    }

    fun getHelloWorldString(): String {
        val pref = context.getSharedPreferences("TEST", Context.MODE_PRIVATE)
        return pref.getString("hello_world", "EMPTY") ?: "EMPTY"
    }
}