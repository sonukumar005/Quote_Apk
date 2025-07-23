package com.example.quote_apk.domain.use_case

import com.example.quote_apk.common.Resource
import com.example.quote_apk.data.remote.response.QuotesResponse
import com.example.quote_apk.data.remote.response.toQuotes
import com.example.quote_apk.domain.model.Quotes
import com.example.quote_apk.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetQuotes_usecase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
     operator fun invoke(): Flow<Resource<List<Quotes>>> = flow{
         try {
             emit(Resource.Loading())
             val quotes = quoteRepository.getQuotes().map { it.toQuotes()  }
             emit(Resource.Success(quotes))
         }
            catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            }
             catch (e: IOException) {
                 emit(Resource.Error("Couldn't reach server. Check your internet connection."))
         }
    }

}