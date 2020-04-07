package com.ulvijabbarli.pronote.data

import android.content.Context
import com.ulvijabbarli.pronote.data.local.db.DbHelper
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesManager
import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager : DataHelper {

    companion object {
        val TAG = DataHelper::class.qualifiedName
    }

    @Inject
    lateinit var prefHelper: PreferencesManager

    @Inject
    lateinit var dbHelper: DbHelper

    @Inject
    lateinit var context: Context

    override fun setAccessToken(token: String) {
        prefHelper.setAccessToken(token)
    }

    override fun getAccessToken(): String? {
        return prefHelper.getAccessToken()
    }

    override fun getAllNote(): Observable<MutableList<Note>> {
        return dbHelper.getAllNote()
    }

    override fun getNote(id: Long): Observable<Note> {
        return dbHelper.getNote(id)
    }

    override fun insertNote(note: Note): Observable<Boolean> {
        return dbHelper.insertNote(note)
    }

    override fun deleteNote(id: Long): Observable<Boolean> {
        return dbHelper.deleteNote(id)
    }

    override fun deleteAllNote(): Observable<Boolean> {
        return dbHelper.deleteAllNote()
    }
}