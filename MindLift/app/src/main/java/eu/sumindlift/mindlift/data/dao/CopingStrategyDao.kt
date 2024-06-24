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
    fun getAllFlow(): Flow<List<CopingStrategy>>

    @Query("SELECT * FROM coping_strategies")
    suspend fun getAll(): List<CopingStrategy>

    @Query("SELECT * FROM coping_strategies WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<CopingStrategy>

    @Query("SELECT * FROM coping_strategies WHERE energy_level = :energyLevel ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWithEnergyLevel(energyLevel: Int): CopingStrategy?

    @Query("SELECT * FROM coping_strategies ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): CopingStrategy

    @Query("SELECT * FROM coping_strategies WHERE id = :id")
    fun getCopingStrategy(id: Int): Flow<CopingStrategy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCopingStrategy(copingStrategy: CopingStrategy)

    @Update
    suspend fun updateCopingStrategy(copingStrategy: CopingStrategy)

    @Delete
    suspend fun delete(copingStrategy: CopingStrategy)
}