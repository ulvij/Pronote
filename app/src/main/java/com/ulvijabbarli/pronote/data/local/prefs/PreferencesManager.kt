package com.ulvijabbarli.pronote.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(var pref: SharedPreferences) : PreferencesHelper {

    companion object PrefKeys {
        const val PREF_NAME = "PRONOTE_PREFERENCES"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    override fun setAccessToken(token: String) {
        pref.edit().putString(ACCESS_TOKEN, token).apply()
    }

    override fun getAccessToken(): String? {
        return pref.getString(ACCESS_TOKEN,null)
    }
}