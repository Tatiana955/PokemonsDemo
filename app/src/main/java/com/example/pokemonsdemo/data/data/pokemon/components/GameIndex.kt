package com.example.pokemonsdemo.data.data.pokemon.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndex(
    @SerialName("game_index")
    val gameIndex: Int,
    val version: Version
)

@Serializable
data class Version(
    val name: String,
    val url: String
)