package com.example.quote_apk.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun QuotesListScreen(
    viewModel: AppViewModel = hiltViewModel(),
    swipeThreshold: Float = 400f,
    sensitivityFactor: Float = 2f,
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray) // Light gray background
        ,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(500.dp)
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

                .background(Color.White)
                .border(
                    width = 2.dp, // Thickness of the border
                    color = Color.Red // Color of the border
                ),
        contentAlignment = Alignment.Center
        ) {

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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(state.quotes) { quote ->
                            QuotesListItem(quotes = quote)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ){
                      IconButton(
                          onClick = {
                          },
                          modifier = Modifier
                              .padding(8.dp)
                      ) {
                          Icon(
                              imageVector = Icons.Default.Save,
                              contentDescription = "save",
                              tint = Color.Black,
                              modifier = Modifier
                                  .padding(8.dp)
                                  .background(Color.White)
                                  .border(1.dp, Color.Black)

                          )
                      }
                        IconButton(
                          onClick = {

                          },
                          modifier = Modifier
                              .padding(8.dp)
                      ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "save",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .background(Color.White)
                                    .border(1.dp, Color.Black)
                            )
                      }
                    }
                }

            }

        }

    }
}


