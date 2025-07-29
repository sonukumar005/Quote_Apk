package com.example.quote_apk.presentation.savedQuotesList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quote_apk.presentation.savedQuotesList.component.ListElement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedQuotesScreen(
    modifier: Modifier = Modifier,
    viewModel: savedQuotesViewModel = hiltViewModel()
) {

    val quotes = viewModel.QuoteList.value.quotes

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Saved Quotes") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(quotes) { quote ->
                ListElement(quote = quote.toEntity(),
                    onDeleted = { viewModel.onEvent(SavedQuotesEvent.DeleteQuote(quote)) },
                    onShare = { viewModel.onEvent(SavedQuotesEvent.ShareQuote(quote)) }
                )
            }
        }
    }
}