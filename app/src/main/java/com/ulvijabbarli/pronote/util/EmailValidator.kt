package com.ulvijabbarli.pronote.util

import androidx.core.util.PatternsCompat

object EmailValidator {

    fun isValidEmail(email:String):Boolean{
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}