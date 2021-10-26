package com.example.pokemonsdemo.data.data.pokemon.components

import kotlinx.serialization.Serializable

@Serializable
data class TypeA(
    val slot: Int,
    val type: TypeB
)

@Serializable
data class TypeB(
    val name: String,
    val url: String
)