package com.example.quote_apk.data.remote

import com.example.quote_apk.common.Resource
import com.example.quote_apk.data.remote.response.QuotesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers

interface apiService {
    @GET("/v1/quotes")
    @Headers("X-API-Key: 7/FojU4FkjhftFGDPOldow==W5gbxyDtBcr0yhoq")
    suspend fun getQuotes():List<QuotesResponse>
}

