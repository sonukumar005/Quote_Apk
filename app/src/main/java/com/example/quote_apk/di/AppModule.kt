package com.example.quote_apk.di

import android.app.Application
import androidx.room.Room
import com.example.quote_apk.common.Constants.BASE_URL
import com.example.quote_apk.data.local.QuotesDatabase
import com.example.quote_apk.data.remote.apiService
import com.example.quote_apk.data.repository.QuoteRepositoryImpl
import com.example.quote_apk.domain.repository.QuoteRepository
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
    fun provideQuoteRepository(api: apiService, db: QuotesDatabase): QuoteRepository {
        return QuoteRepositoryImpl(api, db.dao)
    }

//    @Provides
//    fun provideGetQuotesUseCase(quoteRepository: QuoteRepository): UseCase {
//        return UseCase(
//            getQuotes = GetQuotes_usecase(quoteRepository),
//            getAllSavedQuotes = GetAllSavedQuotes_usecase(quoteRepository),
//            addQuote = AddQuote_usecase(quoteRepository),
//            deleteQuote = DeleteQuote_usecase(quoteRepository)
//            )
//    }

    @Provides
    @Singleton
    fun provideRoomInstance(app: Application): QuotesDatabase =
        Room.databaseBuilder(
            app,
            QuotesDatabase::class.java,
            QuotesDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()


}