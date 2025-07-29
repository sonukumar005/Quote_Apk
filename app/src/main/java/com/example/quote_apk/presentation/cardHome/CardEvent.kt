package com.example.quote_apk.presentation.cardHome

import com.example.quote_apk.data.local.entity.QuotesEntity
import com.example.quote_apk.domain.model.Quotes

sealed class CardEvent {
    data class AddQuote(val quote: Quotes) : CardEvent()
    object shareQuote : CardEvent()
}