package eu.sumindlift.mindlift.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.data.repository.EnergyLevelRecordRepository

class ViewModelFactory(
    private val energyLevelRecordRepository: EnergyLevelRecordRepository,
    private val copingStrategyRepository: CopingStrategyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnergyLevelViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(EnergyLevelRecordRepository::class.java)
                .newInstance(energyLevelRecordRepository)
        }
        if (modelClass.isAssignableFrom(CopingStrategyFormViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(CopingStrategyRepository::class.java)
                .newInstance(copingStrategyRepository)
        }
        if (modelClass.isAssignableFrom(CopingStrategySuggestionViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(CopingStrategyRepository::class.java)
                .newInstance(copingStrategyRepository)
        }
        if (modelClass.isAssignableFrom(EnergyLevelProgressViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(EnergyLevelRecordRepository::class.java)
                .newInstance(energyLevelRecordRepository)
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}