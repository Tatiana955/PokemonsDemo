package com.example.pokemonsdemo.data.models.remote

import com.example.pokemonsdemo.data.data.pokemon.Pokemon
import com.example.pokemonsdemo.data.data.pokemonlist.PokemonList

interface ApiService {

    suspend fun getPokemonList(): PokemonList?

    suspend fun getPokemonInfo(name: String): Pokemon?
}
