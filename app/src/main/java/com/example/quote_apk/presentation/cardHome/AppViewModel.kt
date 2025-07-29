package com.example.quote_apk.presentation.cardHome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote_apk.common.Resource
import com.example.quote_apk.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel  @Inject constructor(
    private val repository: QuoteRepository
)   : ViewModel() {

    private val _quotes = mutableStateOf(QuotesState())
    val quotes: State<QuotesState> = _quotes

    init {
        fetchQuotes()
    }

fun onEvent(event: CardEvent) {
    when (event) {
        is CardEvent.AddQuote -> {
            viewModelScope.launch {
                repository.insertQuote(quote = event.quote)
            }

        }
        is CardEvent.shareQuote -> {

        }
    }
}
     fun fetchQuotes() {
       repository.getQuotes().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _quotes.value = QuotesState(quotes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _quotes.value = QuotesState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _quotes.value = QuotesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}