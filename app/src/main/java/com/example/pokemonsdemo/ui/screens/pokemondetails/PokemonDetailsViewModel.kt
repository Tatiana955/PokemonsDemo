package com.example.pokemonsdemo.ui.screens.pokemondetails

import androidx.lifecycle.ViewModel
import com.example.pokemonsdemo.data.data.pokemon.Pokemon
import com.example.pokemonsdemo.data.repository.Repository
import com.example.pokemonsdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}