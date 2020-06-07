package com.ulvijabbarli.pronote.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ulvijabbarli.pronote.R

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String? = null,
    var description: String? = null,
    var createdDate: Long? = System.currentTimeMillis()
) {


    val isInvalid: Boolean
        get() = title.isNullOrEmpty() || description.isNullOrEmpty()
}