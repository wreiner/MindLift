package eu.sumindlift.mindlift.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Test (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
)