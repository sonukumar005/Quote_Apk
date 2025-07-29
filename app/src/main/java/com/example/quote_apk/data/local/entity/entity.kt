package com.example.quote_apk.data.local.entity

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quote_apk.domain.model.Quotes

@Entity(tableName = "saved_quotes")
data class QuotesEntity(
    val author: String,
    val category: String,
   @PrimaryKey val quote:  String
)
fun QuotesEntity.toDomain() = Quotes( quote = quote, author = author, category = category)
fun Quotes.toEntity() = QuotesEntity(quote = quote, author = author, category = category)