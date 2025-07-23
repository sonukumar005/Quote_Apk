package com.example.quote_apk.data.repository

import com.example.quote_apk.data.remote.apiService
import com.example.quote_apk.data.remote.response.QuotesResponse
import com.example.quote_apk.domain.model.Quotes
import com.example.quote_apk.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(private val api: apiService) :QuoteRepository {
     override suspend fun getQuotes(): List<QuotesResponse> {
       return api.getQuotes()
     }

 }