package com.example.composepokedex.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composepokedex.data.model.PokedexListEntry
import com.example.composepokedex.data.model.response.PokemonName

@Database(entities = [
    PokemonName::class,
    PokedexListEntry::class], version = 2)
abstract class PokemonDb : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        private var INSTANCE: PokemonDb? = null

        fun getInstance(context: Context): PokemonDb {
            if (INSTANCE == null){
                synchronized(PokemonDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDb::class.java, "pokemon-db.db"
                    )
                        .fallbackToDestructiveMigration(true)
                        .build()
                }
            }


        return INSTANCE!!
        }


    }
}
