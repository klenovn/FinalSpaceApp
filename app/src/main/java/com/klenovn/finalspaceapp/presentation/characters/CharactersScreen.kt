package com.klenovn.finalspaceapp.presentation.characters

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Button(onClick = {
        Log.d("CharactersScreen", state.value.toString())
    }) {
        Text(text = "Start")
    }
}