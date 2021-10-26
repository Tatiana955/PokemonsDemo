package com.example.pokemonsdemo.data.models.local

import androidx.room.*
import com.example.pokemonsdemo.data.data.pokemonlist.Result

@Dao
interface PokemonDao {

    @Query("SELECT * FROM result")
    suspend fun getResult(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultList(result: List<Result>)
}
