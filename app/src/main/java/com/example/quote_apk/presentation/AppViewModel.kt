package com.example.quote_apk.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote_apk.common.Resource
import com.example.quote_apk.domain.model.Quotes
import com.example.quote_apk.domain.use_case.GetQuotes_usecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel  @Inject constructor(
    private val getQuotesUseCase: GetQuotes_usecase
)   : ViewModel() {

    private val _quotes = mutableStateOf(QuotesState())
    val quotes: State<QuotesState> = _quotes

    init {
        fetchQuotes()
    }

     fun fetchQuotes() {
       getQuotesUseCase().onEach { result ->
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