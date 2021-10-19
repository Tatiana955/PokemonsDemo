package com.example.pokemonsdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import coil.annotation.ExperimentalCoilApi
import com.example.pokemonsdemo.ui.screens.AppScreen
import com.example.pokemonsdemo.ui.theme.PokemonsDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonsDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        AppScreen()
                    }
                }
            }
        }
    }
}