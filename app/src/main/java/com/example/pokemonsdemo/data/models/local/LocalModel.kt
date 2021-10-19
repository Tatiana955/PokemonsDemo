package com.example.pokemonsdemo.data.models.local

import android.content.Context
import androidx.room.*
import com.example.pokemonsdemo.data.data.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalModel @Inject constructor(@ApplicationContext context: Context): PokemonDao {

    private val database: PokemonDatabase = Room.databaseBuilder(
        context,
        PokemonDatabase::class.java, "weather_db"
    ).build()

    override suspend fun getResult(): List<Result> {
        return database.pokemonDao().getResult()
    }

    override suspend fun insertResultList(result: List<Result>) {
        database.pokemonDao().insertResultList(result)
    }
}