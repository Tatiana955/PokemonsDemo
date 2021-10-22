package com.example.pokemonsdemo.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.pokemonsdemo.ui.AppNavHost
import com.example.pokemonsdemo.ui.screens.components.PokemonsDrawer
import com.example.pokemonsdemo.ui.screens.components.PokemonsTopBar

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            PokemonsTopBar(
                scope = scope,
                scaffoldState = scaffoldState
            )
        },
        content = { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        },
        drawerContent = {
            PokemonsDrawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
    )
}