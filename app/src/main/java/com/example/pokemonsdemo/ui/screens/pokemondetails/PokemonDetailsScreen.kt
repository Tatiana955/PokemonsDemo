package com.example.pokemonsdemo.ui.screens.pokemondetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.pokemonsdemo.data.data.pokemon.Pokemon
import com.example.pokemonsdemo.data.data.pokemon.components.TypeA
import com.example.pokemonsdemo.ui.screens.components.PokemonsCircularProgressIndicator
import com.example.pokemonsdemo.util.Resource
import com.example.pokemonsdemo.util.parseTypeToColor
import java.util.*

@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    dominantColor: Color,
    pokemonName: String,
    viewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value

    Box(modifier = modifier
        .fillMaxSize()
        .background(dominantColor)
        .padding(bottom = 16.dp)
    ) {
        PokemonDetailStateWrapper(
            pokemonInfo = pokemonInfo,
            modifier = modifier
        )
        PokemonDetailImage(
            pokemonInfo = pokemonInfo
        )
    }
}

@Composable
private fun PokemonDetailStateWrapper(
    pokemonInfo: Resource<Pokemon>,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 20.dp + 200.dp / 2f,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.surface)
    ) {
        when(pokemonInfo) {
            is Resource.Success -> {
                PokemonDetailSuccess(
                    pokemonInfo = pokemonInfo.data!!,
                    modifier = modifier
                )
            }
            is Resource.Error -> {
                PokemonDetailError(
                    pokemonInfo = pokemonInfo,
                    modifier = modifier
                )
            }
            is Resource.Loading -> {
                PokemonsCircularProgressIndicator(
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun PokemonDetailSuccess(
    pokemonInfo: Pokemon,
    modifier: Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        PokemonInfoIdAndName(
            pokemonInfo = pokemonInfo
        )
        PokemonInfoType(
            types = pokemonInfo.types
        )
    }
}

@Composable
private fun PokemonInfoIdAndName(
    pokemonInfo: Pokemon
) {
    Text(
        text = "#${pokemonInfo.id} ${pokemonInfo.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }}",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
private fun PokemonInfoType(types: List<TypeA>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        for(i in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(i))
                    .height(35.dp)
            ) {
                Text(
                    text = i.type.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
private fun PokemonDetailError(
    pokemonInfo: Resource<Pokemon>,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = pokemonInfo.message!!,
            color = Color.Red,
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PokemonDetailImage(
    pokemonInfo: Resource<Pokemon>
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(pokemonInfo is Resource.Success) {
            pokemonInfo.data?.sprites?.let {
                Image(
                    painter = rememberImagePainter(
                        data = it.frontDefault,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = pokemonInfo.data.name,
                    modifier = Modifier
                        .size(200.dp)
                        .offset(y = 20.dp)
                )
            }
        }
    }
}



