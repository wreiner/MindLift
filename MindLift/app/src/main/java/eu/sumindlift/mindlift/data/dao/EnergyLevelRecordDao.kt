package eu.sumindlift.mindlift.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import eu.sumindlift.mindlift.data.entity.EnergyLevelRecord

@Dao
interface EnergyLevelRecordDao {
    @Query("SELECT * FROM energy_level_records")
    suspend fun getAll(): List<EnergyLevelRecord>

    @Query("SELECT * FROM energy_level_records ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getAllDescendingAndLimit(limit: Int): List<EnergyLevelRecord>

    @Insert
    suspend fun insert(energyLevelRecord: EnergyLevelRecord)

    @Delete
    suspend fun delete(test: EnergyLevelRecord)
}