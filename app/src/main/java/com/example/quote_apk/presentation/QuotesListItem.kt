package com.example.quote_apk.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quote_apk.domain.model.Quotes

@Composable
fun QuotesListItem(
    quotes: Quotes
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = quotes.quote,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 15.sp,

            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(
                    text = "Author: ",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    )
                Text(text = quotes.author,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }

    }
}