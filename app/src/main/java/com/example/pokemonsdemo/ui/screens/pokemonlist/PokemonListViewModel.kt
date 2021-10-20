package com.example.pokemonsdemo.ui.screens.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokemonsdemo.data.data.PokemonListEntry
import com.example.pokemonsdemo.data.repository.Repository
import com.example.pokemonsdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getPokemonList()
    }

    var pokemonListEntry = mutableStateOf<List<PokemonListEntry>>(listOf())
    var loadError = mutableStateOf("")

    private fun getPokemonList() {
        viewModelScope.launch {
            when (val listResult = repository.getPokemonList()) {
                is Resource.Success -> {
                    val pokemonEntries = listResult.data!!.mapIndexed { _, result ->
                        val number = if (result.url.endsWith("/")) {
                            result.url.dropLast(1).takeLastWhile { char ->
                                char.isDigit()
                            }
                        } else {
                            result.url.takeLastWhile { char ->
                                char.isDigit()
                            }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(result.name.replaceFirstChar { char ->
                            if (char.isLowerCase()) char.titlecase(
                                Locale.ROOT
                            ) else char.toString()
                        }, url, number.toInt())
                    }
                    loadError.value = ""
                    pokemonListEntry.value += pokemonEntries
                }
                is Resource.Error -> {
                    loadError.value = "Error"
                }
                else -> Log.d(TAG, "Unknown error")
            }
        }
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    companion object {
        const val TAG = "!!!PokemonListViewModel"
    }
}