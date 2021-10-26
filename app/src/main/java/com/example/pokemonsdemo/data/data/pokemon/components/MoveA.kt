package com.example.pokemonsdemo.data.data.pokemon.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveA(
    val move: MoveB,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)

@Serializable
data class MoveB(
    val name: String,
    val url: String
)

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Int,
    @SerialName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod,
    @SerialName("version_group")
    val versionGroup: VersionGroup
)

@Serializable
data class MoveLearnMethod(
    val name: String,
    val url: String
)

@Serializable
data class VersionGroup(
    val name: String,
    val url: String
)