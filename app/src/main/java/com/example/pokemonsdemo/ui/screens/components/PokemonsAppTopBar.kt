package com.example.pokemonsdemo.ui.screens.components

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pokemonsdemo.R
import com.example.pokemonsdemo.ui.theme.PokemonsDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PokemonsTopBar(
    scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontFamily = FontFamily.Serif,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(Icons.Filled.Menu, "Menu")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    Toast.makeText(context, "In developing", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Preview
@Composable
private fun PokemonsTopBarPreview() {
    PokemonsDemoTheme(darkTheme = false) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(
            rememberDrawerState(DrawerValue.Closed)
        )
        PokemonsTopBar(scope = scope, scaffoldState = scaffoldState)
    }
}

@Preview
@Composable
private fun PokemonsTopBarPreviewDark() {
    PokemonsDemoTheme(darkTheme = true) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(
            rememberDrawerState(DrawerValue.Closed)
        )
        PokemonsTopBar(scope = scope, scaffoldState = scaffoldState)
    }
}