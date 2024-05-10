package eu.sumindlift.mindlift.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.sumindlift.mindlift.data.repository.EnergyLevelRecordRepository

class ViewModelFactory(private val repository: EnergyLevelRecordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnergyLevelViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(EnergyLevelRecordRepository::class.java)
                .newInstance(repository)
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}