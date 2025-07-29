package com.example.quote_apk.presentation.savedQuotesList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote_apk.data.local.entity.QuotesEntity
import com.example.quote_apk.data.local.entity.toDomain
import com.example.quote_apk.domain.model.Quotes
import com.example.quote_apk.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class savedQuotesViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private val _QuoteList = mutableStateOf(
        SavedQuotesState()
    )
    val QuoteList: State<SavedQuotesState> = _QuoteList

    init {
        fetchSavedQuotes()
    }
    fun onEvent(event: SavedQuotesEvent) {
        when (event) {
            is SavedQuotesEvent.DeleteQuote -> {
                viewModelScope.launch {
                    repository.deleteQuote(event.quote)
                }
            }
            is SavedQuotesEvent.ShareQuote -> {
                viewModelScope.launch {
                    repository.insertQuote(event.quote)
                }
            }
        }
    }


    private fun fetchSavedQuotes() {
        viewModelScope.launch {
            repository.getAllSavedQuotes().collect {
                _QuoteList.value = SavedQuotesState(quotes = it.map { quoteEntity ->
                    quoteEntity.toDomain()
                })
            }
        }
    }
}

data class SavedQuotesState(
    val quotes: List<Quotes> = emptyList(),
)