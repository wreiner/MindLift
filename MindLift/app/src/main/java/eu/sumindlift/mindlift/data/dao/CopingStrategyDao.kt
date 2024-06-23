package eu.sumindlift.mindlift.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface CopingStrategyDao {
    @Query("SELECT * FROM coping_strategies ORDER BY energy_level ASC")
    fun getAllFlow(): Flow<List<CopingStrategy>>

    @Query("SELECT * FROM coping_strategies")
    suspend fun getAll(): List<CopingStrategy>

    @Query("SELECT * FROM coping_strategies WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<CopingStrategy>

    @Query("SELECT * FROM coping_strategies WHERE energy_level = :energyLevel ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWithEnergyLevel(energyLevel: Int): CopingStrategy

    @Query("SELECT * FROM coping_strategies ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): CopingStrategy

    @Insert
    suspend fun insert(copingStrategy: CopingStrategy)

    @Delete
    suspend fun delete(copingStrategy: CopingStrategy)
}