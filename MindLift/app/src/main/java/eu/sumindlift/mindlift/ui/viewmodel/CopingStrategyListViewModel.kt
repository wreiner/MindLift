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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CopingStrategyListViewModel @Inject constructor(private val copingStrategyRepository: CopingStrategyRepository) : ViewModel() {

    private val _copingStrategies = MutableStateFlow(listOf<CopingStrategy>())
    val copingStrategies: StateFlow<List<CopingStrategy>> = _copingStrategies

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean = _onLoading

    suspend fun loadAllCopingStrategies() {
        viewModelScope.launch {
            _onLoading = true
            _copingStrategies.value = copingStrategyRepository.getAll()
            _onLoading = false
        }
    }

    suspend fun delete(copingStrategy: CopingStrategy) {
        copingStrategyRepository.delete(copingStrategy)
        _copingStrategies.update { copingStrategyRepository.getAll() }
    }

}