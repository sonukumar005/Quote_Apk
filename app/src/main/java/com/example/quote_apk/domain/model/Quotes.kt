package com.example.quote_apk.domain.model

import com.example.quote_apk.data.local.entity.QuotesEntity

data class Quotes(
    val author: String,
    val category: String,
    val quote: String
){
    fun toEntity(): QuotesEntity {
        return QuotesEntity(
            author = author,
            category = category,
            quote = quote
        )
    }
}