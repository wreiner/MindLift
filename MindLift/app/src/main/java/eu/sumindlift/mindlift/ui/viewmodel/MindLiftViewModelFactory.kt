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
        if (modelClass.isAssignableFrom(AddCopingStrategyViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(AddCopingStrategyViewModel::class.java)
                .newInstance(copingStrategyRepository)
        }
        if (modelClass.isAssignableFrom(GetCopingStrategyViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(GetCopingStrategyViewModel::class.java)
                .newInstance(copingStrategyRepository)
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}