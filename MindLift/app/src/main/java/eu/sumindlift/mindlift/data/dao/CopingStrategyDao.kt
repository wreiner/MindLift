package eu.sumindlift.mindlift.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface CopingStrategyDao {
    @Query("SELECT * FROM coping_strategies ORDER BY energy_level ASC")
    suspend fun getAll(): List<CopingStrategy>

    @Query("SELECT * FROM coping_strategies WHERE energy_level = :energyLevel ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomByEnergyLevel(energyLevel: Int): CopingStrategy?

    @Query("SELECT * FROM coping_strategies ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): CopingStrategy?

    @Query("SELECT * FROM coping_strategies WHERE id = :id")
    fun getById(id: Int): Flow<CopingStrategy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(copingStrategy: CopingStrategy)

    @Update
    suspend fun update(copingStrategy: CopingStrategy)

    @Delete
    suspend fun delete(copingStrategy: CopingStrategy)
}