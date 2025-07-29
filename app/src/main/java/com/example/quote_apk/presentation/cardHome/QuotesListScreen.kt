package com.example.quote_apk.presentation.cardHome

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quote_apk.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesListScreen(
    viewModel: AppViewModel = hiltViewModel(),
    swipeThreshold: Float = 400f,
    sensitivityFactor: Float = 2f,
    navController: NavHostController,
) {
    val state = viewModel.quotes.value

    var offset by remember { mutableStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            delay(300)
            viewModel.fetchQuotes()
            dismissRight = false
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            delay(300)
            viewModel.fetchQuotes()
            dismissLeft = false
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            IconButton(
                onClick = {
                    navController.navigate("saved")
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmarks,
                    contentDescription = "Saved",
                    tint = Color.Black
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.DarkGray), // Light gray background
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Card(
                modifier = Modifier
                    .padding(30.dp)
                    .offset { IntOffset(offset.roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(onDragEnd = {
                            offset = 0f
                        }) { change, dragAmount ->

                            offset += (dragAmount / density) * sensitivityFactor
                            when {
                                offset > swipeThreshold -> {
                                    dismissRight = true
                                }

                                offset < -swipeThreshold -> {
                                    dismissLeft = true
                                }
                            }
                            if (change.positionChange() != Offset.Zero) change.consume()
                        }
                    }
                    .graphicsLayer(
                        alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f).value,
                        rotationZ = animateFloatAsState(offset / 50).value
                    )
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.cardbackground),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    when {
                        state.isLoading -> {
                            CircularProgressIndicator()
                        }

                        state.error.isNotBlank() -> {
                            Text(
                                text = state.error,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }

                        else -> {
                            QuotesListItem(quote = state.quotes.first())
                        }

                    }
                }
            }

        }
    }
}


