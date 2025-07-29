package com.example.quote_apk.data.repository

import com.example.quote_apk.common.Resource
import com.example.quote_apk.data.local.dao.Dao
import com.example.quote_apk.data.local.entity.QuotesEntity
import com.example.quote_apk.data.remote.apiService
import com.example.quote_apk.data.remote.response.toQuotes
import com.example.quote_apk.domain.model.Quotes
import com.example.quote_apk.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class QuoteRepositoryImpl (
    private val api: apiService,
     private val quotesDao: Dao
) : QuoteRepository {
    override  fun getQuotes(): Flow<Resource<List<Quotes>>> = flow{
        try {
            emit(Resource.Loading())
            val result = api.getQuotes().map { it.toQuotes() }
            emit(Resource.Success(result))
        }
        catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    override suspend fun insertQuote(quote: Quotes) {
        quotesDao.insertQuote(quote.toEntity())
    }

    override suspend fun deleteQuote(quote: Quotes) {
        quotesDao.deleteQuote(quote.toEntity())
    }

    override fun getAllSavedQuotes(): Flow<List<QuotesEntity>> {
        return quotesDao.getAllQuotes()
    }
}