package eu.sumindlift.mindlift.data.repositories

import eu.sumindlift.mindlift.data.daos.TestDao
import eu.sumindlift.mindlift.data.entities.Test
import javax.inject.Inject

class TestRepository @Inject constructor (private val testDao: TestDao) {
    fun getAll(): List<Test> {
        return testDao.getAll()
    }

    fun insertTask(test: Test) {
        return testDao.insertAll(test)
    }

    fun deleteTask(test: Test) {
        return testDao.delete(test)
    }
}