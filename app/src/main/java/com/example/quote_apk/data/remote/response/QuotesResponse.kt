package com.example.quote_apk.data.remote.response

import com.example.quote_apk.domain.model.Quotes

data class QuotesResponse(
    val author: String,
    val category: String,
    val quote: String
)

fun QuotesResponse.toQuotes(): Quotes {
    return Quotes(
        author = author,
        category = category,
        quote = quote
    )

}