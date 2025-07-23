package com.example.quote_apk.domain.repository

import com.example.quote_apk.data.remote.response.QuotesResponse
import com.example.quote_apk.domain.model.Quotes

interface QuoteRepository {
    suspend fun getQuotes(): List<QuotesResponse>
}