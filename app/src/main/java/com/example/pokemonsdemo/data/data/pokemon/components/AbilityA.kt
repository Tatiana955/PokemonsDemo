package com.example.pokemonsdemo.data.data.pokemon.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityA(
    val ability: AbilityB,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)

@Serializable
data class AbilityB(
    val name: String,
    val url: String
)