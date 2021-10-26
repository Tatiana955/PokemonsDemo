package com.example.pokemonsdemo.data.repository

import com.example.pokemonsdemo.data.data.pokemon.Pokemon
import com.example.pokemonsdemo.data.data.pokemonlist.PokemonList
import com.example.pokemonsdemo.data.data.pokemonlist.Result
import com.example.pokemonsdemo.data.models.local.LocalModel
import com.example.pokemonsdemo.data.models.remote.RemoteModel
import com.example.pokemonsdemo.util.Resource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteModel,
    private val local: LocalModel
) {

    suspend fun getPokemonList(): Resource<List<Result>> {
        val remoteList: PokemonList?
        val localList: List<Result>
        try {
            remoteList = remote.getPokemonList()
            if (remoteList != null) {
                localList = remoteList.results!!
                local.insertResultList(localList)
            }
        } catch(e: Exception) {
            return Resource.Error("Error: ${e.message}")
        }
        return Resource.Success(local.getResult())
    }

    suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        val pokemon: Pokemon
        try {
            pokemon = remote.getPokemonInfo(name)!!
        } catch(e: Exception) {
            return Resource.Error("Error: ${e.message}")
        }
        return Resource.Success(pokemon)
    }
}