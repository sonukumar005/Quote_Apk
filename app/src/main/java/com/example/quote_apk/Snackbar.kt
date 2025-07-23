package com.example.quote_apk

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun Snackbar(modifier: Modifier = Modifier) {
    val snackbarHostState = remember{
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            contentAlignment = Alignment.Center
        ) {
           Button(
               onClick = {
                   scope.launch {
                       snackbarHostState.showSnackbar(
                           message = "This is a Snackbar",
                           actionLabel = "Dismiss",
                           withDismissAction = false,
                           duration = SnackbarDuration.Short
                       )
                   }
               }
           ) {
               Text(text = "Show Snackbar")
           }
        }
    }
}