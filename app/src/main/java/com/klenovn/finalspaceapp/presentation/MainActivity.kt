package com.klenovn.finalspaceapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.klenovn.finalspaceapp.presentation.ui.theme.FinalSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        Text(text = "Hello World!")
                    }
                }
            }
        }
    }
}