package com.example.pokemonsdemo.data.models.remote

import com.example.pokemonsdemo.data.data.PokemonList

interface ApiService {

    suspend fun getPokemonList(): PokemonList?
}
