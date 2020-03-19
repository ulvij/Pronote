package com.ulvijabbarli.pronote.data.local.db

import androidx.lifecycle.LiveData
import com.ulvijabbarli.pronote.data.model.Note

interface DbHelper {

    fun getAllNote(): LiveData<MutableList<Note>>

    fun getNote(id:Long):LiveData<Note>

    fun insertNote(note:Note):LiveData<Boolean>

    fun deleteNote(id:Long):LiveData<Boolean>

    fun deleteAllNote():LiveData<Boolean>

}