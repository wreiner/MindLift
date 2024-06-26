package eu.sumindlift.mindlift.data.repository

import eu.sumindlift.mindlift.data.dao.CopingStrategyDao
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CopingStrategyRepository @Inject constructor(private val copingStrategyDao: CopingStrategyDao) {

    suspend fun getAll(): List<CopingStrategy> {
        return copingStrategyDao.getAll()
    }

    suspend fun getRandomByEnergyLevel(energyLevel: Int): CopingStrategy? {
        return copingStrategyDao.getRandomByEnergyLevel(energyLevel)
    }

    suspend fun getRandom(): CopingStrategy? {
        return copingStrategyDao.getRandom()
    }

    suspend fun createAndInsert(title: String, description: String, energyLevel: EnergyLevel) {
        val copingStrategy = CopingStrategy(
            null,
            title,
            description,
            energyLevel.getId()
        )
        return copingStrategyDao.insert(copingStrategy)
    }

    fun getById(id: Int): Flow<CopingStrategy> {
        return copingStrategyDao.getById(id)
    }

    suspend fun insert(copingStrategy: CopingStrategy): Boolean {
        return try {
            copingStrategyDao.insert(copingStrategy)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun update(copingStrategy: CopingStrategy): Boolean {
        return try {
            copingStrategyDao.update(copingStrategy)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun delete(copingStrategy: CopingStrategy) {
        copingStrategyDao.delete(copingStrategy)
    }
}