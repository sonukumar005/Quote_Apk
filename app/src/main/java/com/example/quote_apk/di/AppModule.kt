package com.example.quote_apk.di

import com.example.quote_apk.common.Constants.BASE_URL
import com.example.quote_apk.data.remote.apiService
import com.example.quote_apk.data.repository.QuoteRepositoryImpl
import com.example.quote_apk.domain.repository.QuoteRepository
import com.example.quote_apk.domain.use_case.GetQuotes_usecase
import com.example.quote_apk.domain.use_case.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiProvider(): apiService {
        return Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient.Builder().build())
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(apiService::class.java)

    }


    @Provides
    fun provideQuoteRepository(api: apiService): QuoteRepository {
        return QuoteRepositoryImpl(api)
    }

    @Provides
    fun provideGetQuotesUseCase(quoteRepository: QuoteRepository): UseCase {
        return UseCase(
            getQuotes = GetQuotes_usecase(quoteRepository),

            )
    }


}