package com.example.pokemonsdemo.data.models.remote

import android.util.Log
import com.example.pokemonsdemo.data.data.pokemon.Pokemon
import com.example.pokemonsdemo.data.data.pokemonlist.PokemonList
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import javax.inject.Inject
import kotlinx.serialization.json.Json as KotlinJson

class RemoteModel @Inject constructor() : ApiService {

    private val client: HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(KotlinJson { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getPokemonList(): PokemonList? {
        val pokemon: PokemonList? = null
        return try {
            client.get {
                url(POKEMON)
                parameter("limit", LIMIT)
                parameter("offset", OFFSET)
            }
        } catch(e: Exception) {
            Log.d(TAG, "Error in getPokemonList(): ${e.message}")
            pokemon
        }
    }

    override suspend fun getPokemonInfo(name: String): Pokemon? {
        val pokemon: Pokemon? = null
        return try {
            client.get(path = name) {
                url("$POKEMON/$name")
            }
        } catch(e: Exception) {
            Log.d(TAG, "Error in getPokemonInfo(): ${e.message}")
            pokemon
        }
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val POKEMON = "${BASE_URL}/pokemon"

        const val TAG = "!!!RemoteModel"

        const val LIMIT = 10
        const val OFFSET = 10
    }
}