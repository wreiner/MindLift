package eu.sumindlift.mindlift.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.sumindlift.mindlift.api.Quote
import eu.sumindlift.mindlift.api.RetrofitInstance
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InspirationalQuotesViewModel @Inject constructor() : ViewModel() {

    private val quoteApiService = RetrofitInstance.quoteApi
    private var quotes = listOf<Quote>()

    private val _quote = MutableStateFlow(Quote("", ""))
    val quote: StateFlow<Quote> = _quote

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean = _onLoading

    suspend fun loadQuotes() {
        viewModelScope.launch {
            _onLoading = true
            try {
                if (quotes.isEmpty()) {
                    quotes = quoteApiService.getQuotes()
                }
                setRandomQuote()
            } catch (e: Exception) {
                _quote.value = Quote("An error occurred, make sure your device is connected to the internet.", "")
            }
            _onLoading = false
        }
    }

    private fun setRandomQuote() {
        if (quotes.isEmpty()) {
            return
        }
        _quote.value = quotes.random()
    }

}