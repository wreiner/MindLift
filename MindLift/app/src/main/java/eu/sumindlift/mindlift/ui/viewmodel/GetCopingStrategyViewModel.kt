package eu.sumindlift.mindlift.ui.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GetCopingStrategyViewModel @Inject constructor(private val repository: CopingStrategyRepository) : ViewModel() {

    var strategy : CopingStrategy = CopingStrategy(42, "example", "example", 1)
    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean = _onLoading

    fun getCopingStrategy(energyLevel: Int) {
        viewModelScope.launch {
            _onLoading = true
            val newStrategy = repository.getRandomCopingStrategyWithEnergyLevel(energyLevel) ?: null
            if (newStrategy != null) {
                strategy = newStrategy
            }
            _onLoading = false
        }
    }
}
