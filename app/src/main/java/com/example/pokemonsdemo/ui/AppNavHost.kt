package com.example.pokemonsdemo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.pokemonsdemo.ui.screens.pokemonlist.PokemonListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun AppNavHost(navController: NavHostController,
               modifier: Modifier = Modifier,
) {
    NavHost(
        navController,
        startDestination = NavItem.PokemonListScreen.route,
        modifier = modifier
    ) {

        composable(NavItem.PokemonListScreen.route) {
            PokemonListScreen()
        }
    }
}

sealed class NavItem(var route: String, var icon: Int?, var title: String) {
    object PokemonListScreen : NavItem("main", null, "PokemonListScreen")
}
