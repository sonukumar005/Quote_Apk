package com.example.quote_apk.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.quote_apk.data.local.entity.QuotesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuotesEntity)

    @Delete
    suspend fun deleteQuote(quote: QuotesEntity)

    @Query("SELECT * FROM saved_quotes")
     fun getAllQuotes(): Flow<List<QuotesEntity>>
}
