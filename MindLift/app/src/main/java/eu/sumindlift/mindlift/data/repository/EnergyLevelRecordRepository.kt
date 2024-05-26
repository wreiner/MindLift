package eu.sumindlift.mindlift.data.repository

import eu.sumindlift.mindlift.data.dao.EnergyLevelRecordDao
import eu.sumindlift.mindlift.data.entity.EnergyLevelRecord
import java.util.Date
import javax.inject.Inject

class EnergyLevelRecordRepository @Inject constructor(private val energyLevelRecordDao: EnergyLevelRecordDao) {
    suspend fun getAll(): List<EnergyLevelRecord> {
        return energyLevelRecordDao.getAll()
    }

    suspend fun createAndInsertEnergyLevelRecord(level: Int) {
        val energyLevelRecord = EnergyLevelRecord(
            null,
            level,
            "",
            Date().toString()
        )
        return energyLevelRecordDao.insertAll(energyLevelRecord)
    }

}