package com.sublime.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.database.model.MovieCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE category = :category")
    fun observeMoviesByCategory(
        category: MovieCategory
    ): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(
        movies: List<MovieEntity>
    )

    @Query("DELETE FROM movies WHERE category = :category")
    suspend fun deleteMoviesByCategory(
        category: MovieCategory
    )
}