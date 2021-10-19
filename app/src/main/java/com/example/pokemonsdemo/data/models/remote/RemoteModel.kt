package com.example.pokemonsdemo.data.models.remote

import android.util.Log
import com.example.pokemonsdemo.data.data.PokemonList
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
                parameter("pageSize", PAGE_SIZE)
            }
        } catch(e: Exception) {
            Log.d(TAG, "Error: ${e.message}")
            pokemon
        }
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val POKEMON = "${BASE_URL}/pokemon"

        const val TAG = "!!!RemoteModel"

        const val LIMIT = 10
        const val PAGE_SIZE = 10
    }
}