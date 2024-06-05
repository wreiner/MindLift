package eu.sumindlift.mindlift.data.repository

import eu.sumindlift.mindlift.data.dao.CopingStrategyDao
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import javax.inject.Inject

class CopingStrategyRepository @Inject constructor(private val copingStrategyDao: CopingStrategyDao) {
    suspend fun getAll(): List<CopingStrategy> {
        return copingStrategyDao.getAll()
    }

    suspend fun getRandomCopingStrategy(): CopingStrategy {
        return copingStrategyDao.getRandom()
    }

    suspend fun createAndInsertCopingStrategy(title: String, description: String, energyLevel: EnergyLevel) {
        val copingStrategy = CopingStrategy(
            null,
            title,
            description,
            energyLevel.getId()
        )
        return copingStrategyDao.insert(copingStrategy)
    }

}