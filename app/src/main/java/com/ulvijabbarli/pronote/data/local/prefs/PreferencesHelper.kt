package com.ulvijabbarli.pronote.data.local.prefs

interface PreferencesHelper {

    fun setAccessToken(token:String)
    fun getAccessToken():String?
}