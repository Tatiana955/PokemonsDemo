package com.example.pokemonsdemo.data.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonsdemo.data.data.pokemonlist.Result

@Database(
    entities = [
        Result::class
               ],
    exportSchema = false,
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
