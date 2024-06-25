package eu.sumindlift.mindlift.data.repository

import eu.sumindlift.mindlift.data.dao.EnergyLevelRecordDao
import eu.sumindlift.mindlift.data.entity.EnergyLevelRecord
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class EnergyLevelRecordRepository @Inject constructor(private val energyLevelRecordDao: EnergyLevelRecordDao) {
    suspend fun getAll(): List<EnergyLevelRecord> {
        return energyLevelRecordDao.getAll()
    }

    suspend fun createAndInsert(level: Int) {
        val energyLevelRecord = EnergyLevelRecord(
            null,
            level,
            "",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm"))
        )
        return energyLevelRecordDao.insert(energyLevelRecord)
    }

    suspend fun getLatestEnergyLevelRecords(limit: Int): List<EnergyLevelRecord> {
        return energyLevelRecordDao.getAllDescendingAndLimit(limit)
    }

}