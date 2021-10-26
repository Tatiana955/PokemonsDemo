package com.example.pokemonsdemo.data.data.pokemon.components

import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val name: String,
    val url: String
)