package com.example.quote_apk.presentation.savedQuotesList.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quote_apk.data.local.entity.QuotesEntity


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListElement(
    modifier: Modifier = Modifier,
    quote: QuotesEntity = QuotesEntity(
        quote = "Sample quote text goes here." +
                "This is a placeholder for a longer quote that might be saved in the application.",
        author = "Author Name",
        category = "Category",
    ),
    onDeleted: (QuotesEntity) -> Unit = {},
    onShare: (QuotesEntity) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp, end = 30.dp, bottom = 20.dp),
        shape = RoundedCornerShape(16.dp), // Rectangle with rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = " \" ${quote.quote} \"", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "- ${quote.author}", style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold)

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Default.Delete , contentDescription = "Delete Quote",
                    modifier = Modifier.padding(8.dp).clickable {
                        onDeleted(quote)
                    }
                )
                Icon(imageVector = Icons.Default.Share , contentDescription = "Delete Quote",
                    modifier = Modifier.padding(8.dp).clickable {
                        onShare(quote)
                    }
                )
            }
        }
    }
}

