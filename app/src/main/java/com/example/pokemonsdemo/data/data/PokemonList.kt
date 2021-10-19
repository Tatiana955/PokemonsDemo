package com.example.pokemonsdemo.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.*

@Serializable
data class PokemonList(
    val count: Int?,
    val next: String?,
    val results: List<Result>?,
    val previous: String?,
)

@Entity(tableName = "result")
@Serializable
data class Result(
    @PrimaryKey val name: String,
    val url: String
)

data class PokemonListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int
)