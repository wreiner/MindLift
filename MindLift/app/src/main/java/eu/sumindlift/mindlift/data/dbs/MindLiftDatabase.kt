package eu.sumindlift.mindlift.data.dbs

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.sumindlift.mindlift.data.entities.*
import eu.sumindlift.mindlift.data.daos.*

@Database(entities = [Test::class], version = 1)
abstract class MindLiftDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}