package eu.sumindlift.mindlift.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import eu.sumindlift.mindlift.data.entities.Test

@Dao
interface TestDao {
    @Query("SELECT * FROM test")
    fun getAll(): List<Test>

    @Query("SELECT * FROM test WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Test>

    @Query("SELECT * FROM test WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Test

    @Insert
    fun insertAll(vararg tests: Test)

    @Delete
    fun delete(test: Test)
}