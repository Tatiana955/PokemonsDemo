package com.example.pokemonsdemo.data.data.pokemon

import com.example.pokemonsdemo.data.data.pokemon.components.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val abilities: List<AbilityA>,
    @SerialName("base_experience")
    val baseExperience: Int,
    val forms: List<Form>,
    @SerialName("game_indices")
    val gameIndices: List<GameIndex>,
    val height: Int,
    @SerialName("held_items")
    val heldItems: List<EmptyArray>,
    val id: Int,
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<MoveA>,
    val name: String,
    val order: Int,
    @SerialName("past_types")
    val pastTypes: List<EmptyArray>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<StatA>,
    val types: List<TypeA>,
    val weight: Int
)

@Serializable
class EmptyArray