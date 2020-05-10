package com.ulvijabbarli.pronote.data

import android.content.Context
import com.ulvijabbarli.pronote.data.local.db.DbHelper
import com.ulvijabbarli.pronote.data.local.prefs.PreferencesManager
import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    var prefHelper: PreferencesManager,
    var dbHelper: DbHelper,
    var context: Context
) : DataHelper {

    companion object {
        val TAG = DataHelper::class.qualifiedName
    }

    override fun setAccessToken(token: String) {
        prefHelper.setAccessToken(token)
    }

    override fun getAccessToken(): String? {
        return prefHelper.getAccessToken()
    }

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return dbHelper.getAllNote()
    }

    override fun getNote(id: Long): Flowable<Note> {
        return dbHelper.getNote(id)
    }

    override fun insertNote(note: Note): Flowable<Boolean> {
        return dbHelper.insertNote(note)
    }

    override fun deleteNote(id: Long): Flowable<Boolean> {
        return dbHelper.deleteNote(id)
    }

    override fun deleteAllNote(): Flowable<Boolean> {
        return dbHelper.deleteAllNote()
    }
}