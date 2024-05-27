package eu.sumindlift.mindlift.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "energy_level_records")
data class EnergyLevelRecord (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "energy_level") val level: Int?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "timestamp") val timestamp: String?,
)