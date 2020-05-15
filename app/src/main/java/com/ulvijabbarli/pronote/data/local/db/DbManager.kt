package com.ulvijabbarli.pronote.data.local.db

import com.ulvijabbarli.pronote.data.model.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbManager @Inject constructor(var appDatabase: AppDatabase) : DbHelper {

    override fun getAllNote(): Flowable<MutableList<Note>> {
        return appDatabase.noteDao().getAllNote()
    }

    override fun getNote(id: Long): Flowable<Note> {
        return appDatabase.noteDao().getNote(id)
    }

    override fun insertNote(note: Note): Completable {
        return appDatabase.noteDao().insert(note)
    }

    override fun deleteNote(id: Long): Completable {
        return appDatabase.noteDao().deleteNote(id)
    }

    override fun deleteAllNote(): Completable {
        return appDatabase.noteDao().deleteAll()
    }

}