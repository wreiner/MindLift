package eu.sumindlift.mindlift.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.sumindlift.mindlift.data.entity.EnergyLevelRecord
import eu.sumindlift.mindlift.data.repository.EnergyLevelRecordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnergyLevelProgressViewModel @Inject constructor(private val energyLevelRecordRepository: EnergyLevelRecordRepository) : ViewModel() {

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean = _onLoading

    private val _energyLevelRecords = MutableStateFlow(listOf<EnergyLevelRecord>())
    val energyLevelRecords: StateFlow<List<EnergyLevelRecord>> = _energyLevelRecords

    fun loadLatestEnergyLevelRecords(limit: Int) {
        viewModelScope.launch {
            _onLoading = true
            _energyLevelRecords.value = energyLevelRecordRepository.getLatestEnergyLevelRecords(limit)
            _onLoading = false
        }
    }

}