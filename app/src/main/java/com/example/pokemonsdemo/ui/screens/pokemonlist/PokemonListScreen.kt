package com.example.pokemonsdemo.ui.screens.pokemonlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.pokemonsdemo.data.data.pokemonlist.PokemonListEntry
import com.example.pokemonsdemo.ui.NavItem
import com.example.pokemonsdemo.ui.screens.components.PokemonsCircularProgressIndicator
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonListEntry by remember { viewModel.pokemonListEntry }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = pokemonListEntry, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    PokemonList(
                        entry = item,
                        modifier = modifier,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            })
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun PokemonList(
    entry: PokemonListEntry,
    modifier: Modifier,
    viewModel: PokemonListViewModel,
    navController: NavController
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    NavItem.PokemonDetailsScreen.route +
                            "/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            }
    ) {
        Column() {
            val painter = rememberImagePainter(
                data = entry.imageUrl,
                builder = {
                    crossfade(true)
                }
            )
            val painterState = painter.state
            Image(
                painter = painter,
                contentDescription = entry.pokemonName,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
            )
            if (painterState is ImagePainter.State.Loading) {
                PokemonsCircularProgressIndicator(
                    modifier = modifier
                )
            }
            else if (painterState is ImagePainter.State.Success) {
                LaunchedEffect(key1 = painter) {
                    launch {
                        val image = painter.imageLoader.execute(painter.request).drawable
                        viewModel.calculateDominantColor(image!!) {
                            dominantColor = it
                        }
                    }
                }
            }
            Text(
                text = entry.pokemonName,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}