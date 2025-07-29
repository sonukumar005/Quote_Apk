package com.example.quote_apk.domain.use_case

import com.example.quote_apk.domain.repository.QuoteRepository
import javax.inject.Inject

class GetAllSavedQuotes_usecase @Inject constructor(
    private val repository: QuoteRepository
) {
    operator fun invoke() = repository.getAllSavedQuotes()
}