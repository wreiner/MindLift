package eu.sumindlift.mindlift.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import eu.sumindlift.mindlift.data.entity.CopingStrategy

@Dao
interface CopingStrategyDao {
    @Query("SELECT * FROM coping_strategies")
    suspend fun getAll(): List<CopingStrategy>

    @Query("SELECT * FROM coping_strategies WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<CopingStrategy>

    @Insert
    suspend fun insert(copingStrategy: CopingStrategy)

    @Delete
    suspend fun delete(copingStrategy: CopingStrategy)
}