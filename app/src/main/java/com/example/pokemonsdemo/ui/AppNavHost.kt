package com.example.pokemonsdemo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.pokemonsdemo.ui.screens.pokemondetails.PokemonDetailsScreen
import com.example.pokemonsdemo.ui.screens.pokemonlist.PokemonListScreen
import java.util.*

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController,
        startDestination = NavItem.PokemonListScreen.route,
        modifier = modifier
    ) {

        composable(NavItem.PokemonListScreen.route) {
            PokemonListScreen(
                navController = navController
            )
        }
        composable(NavItem.PokemonDetailsScreen.route + "/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailsScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: ""
            )
        }
    }
}

sealed class NavItem(var route: String, var icon: Int?, var title: String) {
    object PokemonListScreen : NavItem("pok_list", null, "PokemonListScreen")
    object PokemonDetailsScreen : NavItem("pok_details", null, "PokemonDetailsScreen")
}
