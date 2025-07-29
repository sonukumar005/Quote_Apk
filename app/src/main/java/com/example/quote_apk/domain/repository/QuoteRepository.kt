package com.example.quote_apk.domain.repository

import com.example.quote_apk.common.Resource
import com.example.quote_apk.data.local.entity.QuotesEntity
import com.example.quote_apk.data.remote.response.QuotesResponse
import com.example.quote_apk.domain.model.Quotes
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
     fun getQuotes():Flow<Resource<List<Quotes>>>
    suspend fun insertQuote(quote: Quotes)
    suspend fun deleteQuote(quote: Quotes)
    fun getAllSavedQuotes(): Flow<List<QuotesEntity>>

}