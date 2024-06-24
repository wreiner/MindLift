package eu.sumindlift.mindlift.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCopingStrategyViewModel @Inject constructor(private val copingStrategyRepository: CopingStrategyRepository) : ViewModel() {

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean = _onLoading

    fun newCopingStrategy(title: String, description: String, energyLevel: EnergyLevel) {
        viewModelScope.launch {
            _onLoading = true
            copingStrategyRepository.createAndInsert(title, description, energyLevel)
            _onLoading = false
        }
    }

}