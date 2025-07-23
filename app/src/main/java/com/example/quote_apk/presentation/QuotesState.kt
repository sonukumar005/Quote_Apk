package com.example.quote_apk.presentation

import com.example.quote_apk.domain.model.Quotes

data class QuotesState(
    val isLoading: Boolean = false,
    val quotes: List<Quotes> = emptyList(),
    val error: String = ""
)