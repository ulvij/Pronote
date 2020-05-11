package com.ulvijabbarli.pronote.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String? = null,
    var description: String? = null,
    var createdDate: Long? = System.currentTimeMillis()
) {

    fun isValid(): Pair<Boolean, String?> {
        if (title != null && description != null) return Pair(true, null)
        val messages = arrayListOf<String>()
        when {
            title == null -> messages.add("Please add title")
            description == null -> messages.add("Please add description")
        }

        return Pair(false, messages.joinToString { "\n" })
    }


}