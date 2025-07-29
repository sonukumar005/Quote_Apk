package com.example.quote_apk.presentation.savedQuotesList

import com.example.quote_apk.data.local.entity.QuotesEntity
import com.example.quote_apk.domain.model.Quotes

sealed class SavedQuotesEvent {
    data class DeleteQuote(val quote: Quotes) : SavedQuotesEvent()
    data class ShareQuote(val quote: Quotes) : SavedQuotesEvent()

}