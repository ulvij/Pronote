package com.ulvijabbarli.pronote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var title: String? = null,
    var description: String? = null,
    var createdDate:Long? = null
)