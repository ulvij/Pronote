package com.ulvijabbarli.pronote.data.local.db

import com.ulvijabbarli.pronote.data.model.Note
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

    override fun insertNote(note: Note): Flowable<Boolean> {
        appDatabase.noteDao().insert(note)
        return Flowable.just(true)
    }

    override fun deleteNote(id: Long): Flowable<Boolean> {
        appDatabase.noteDao().deleteNote(id)
        return Flowable.just(true)
    }

    override fun deleteAllNote(): Flowable<Boolean> {
        appDatabase.noteDao().deleteAll()
        return Flowable.just(true)
    }

}