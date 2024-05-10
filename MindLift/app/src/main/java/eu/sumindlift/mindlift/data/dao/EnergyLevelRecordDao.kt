package eu.sumindlift.mindlift.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import eu.sumindlift.mindlift.data.entity.EnergyLevelRecord

@Dao
interface EnergyLevelRecordDao {
    @Query("SELECT * FROM energy_level_records")
    fun getAll(): List<EnergyLevelRecord>

    @Query("SELECT * FROM energy_level_records WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<EnergyLevelRecord>

    @Insert
    fun insertAll(vararg tests: EnergyLevelRecord)

    @Delete
    fun delete(test: EnergyLevelRecord)
}