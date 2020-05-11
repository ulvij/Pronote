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
        val messages = arrayListOf<String>()
        when {
            title.isNullOrEmpty() -> messages.add("Please add title")
            description.isNullOrEmpty() -> messages.add("Please add description")
        }

        println(messages.toString())
        val fullMessage = messages.joinToString { "\n" }

        println(fullMessage)
        return if (fullMessage.isEmpty())
            Pair(true, null)
        else
            Pair(false, fullMessage)
    }


}