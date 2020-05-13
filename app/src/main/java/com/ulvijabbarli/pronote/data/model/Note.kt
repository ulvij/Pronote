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

        var messages = ""

        if (title.isNullOrEmpty())
            messages = messages.plus("Please add title")

        if (description.isNullOrEmpty())
            messages = messages.plus("\nPlease add description")

        return if (messages.isEmpty())
            Pair(true, null)
        else
            Pair(false, messages)

    }


}