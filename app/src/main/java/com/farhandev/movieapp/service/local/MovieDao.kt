package com.farhandev.movieapp.service.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieModel)

    @Query("SELECT * FROM tb_movie ORDER BY id ASC")
    fun getAllMovie(): List<MovieModel>

    @Query("DELETE FROM tb_movie WHERE id = :userId")
    fun deleteById(userId: Int)
}