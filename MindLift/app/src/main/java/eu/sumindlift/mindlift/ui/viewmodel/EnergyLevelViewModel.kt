package eu.sumindlift.mindlift.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.sumindlift.mindlift.data.repository.EnergyLevelRecordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnergyLevelViewModel @Inject constructor(private val energyLevelRecordRepository: EnergyLevelRecordRepository) : ViewModel() {

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean
    get() = _onLoading

    fun newEnergyLevelRecord(level: Int) {
        viewModelScope.launch {
            _onLoading = true
            energyLevelRecordRepository.createAndInsert(level)
            _onLoading = false
        }
    }

}