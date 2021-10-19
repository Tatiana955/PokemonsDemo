package com.example.pokemonsdemo.ui.screens.pokemonlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.pokemonsdemo.data.data.PokemonListEntry

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonListScreen(
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
                PokemonList(item)
            })
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PokemonList(entry: PokemonListEntry,
                modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column() {
            Image(
                painter = rememberImagePainter(
                    data = entry.imageUrl
                ),
                contentDescription = entry.pokemonName,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )
            Text(
                text = entry.pokemonName,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun PokemonListPreview() {
    PokemonList(entry = FakeData.fakePokemonListEntry,
        modifier = Modifier)
}

private object FakeData {
    val fakePokemonListEntry = PokemonListEntry("name", "url", 1)
}