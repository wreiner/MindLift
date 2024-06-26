package eu.sumindlift.mindlift.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CopingStrategySuggestionViewModel @Inject constructor(private val copingStrategyRepository: CopingStrategyRepository) : ViewModel() {

    private val _strategy = MutableStateFlow(CopingStrategy())
    val strategy: StateFlow<CopingStrategy> = _strategy

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean = _onLoading

    fun loadCopingStrategyOrElseDefault(energyLevel: Int, defaultCopingStrategy: CopingStrategy) {
        viewModelScope.launch {
            _onLoading = true
            val newStrategy = copingStrategyRepository.getRandomByEnergyLevel(energyLevel) ?: defaultCopingStrategy
            _strategy.value = newStrategy
            _onLoading = false
        }
    }

    fun setStrategy(strategy: CopingStrategy) {
        _strategy.value = strategy
    }
}
