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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (createdDate != other.createdDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (createdDate?.hashCode() ?: 0)
        return result
    }
}