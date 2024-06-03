package eu.sumindlift.mindlift.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.sumindlift.mindlift.data.entity.*
import eu.sumindlift.mindlift.data.dao.*

@Database(entities = [EnergyLevelRecord::class, CopingStrategy::class], version = 1)
abstract class MindLiftDatabase : RoomDatabase() {
    abstract fun energyLevelRecordDao(): EnergyLevelRecordDao
    abstract fun copingStrategyDao(): CopingStrategyDao
}