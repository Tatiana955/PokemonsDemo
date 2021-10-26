package com.example.pokemonsdemo.data.data.pokemon.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatA(
    @SerialName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatB
)

@Serializable
data class StatB(
    val name: String,
    val url: String
)