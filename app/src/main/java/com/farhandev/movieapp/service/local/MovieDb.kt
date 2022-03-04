package com.farhandev.movieapp.service.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
abstract class MovieDb: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        private const val DB_NAME = "movie_database.db"
        @Volatile private var instance: MovieDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDb::class.java,
            DB_NAME
        ).allowMainThreadQueries().build()
    }
}