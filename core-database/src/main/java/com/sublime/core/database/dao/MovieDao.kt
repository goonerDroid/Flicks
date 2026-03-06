package com.sublime.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sublime.core.database.entity.MovieEntity
import com.sublime.core.model.BrowseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE category = :category ORDER BY popularity DESC")
    fun observeMoviesByCategory(
        category: BrowseCategory
    ): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(
        movies: List<MovieEntity>
    )

    @Query("DELETE FROM movies WHERE category = :category")
    suspend fun deleteMoviesByCategory(
        category: BrowseCategory
    )

    @Transaction
    suspend fun replaceMovies(
        category: BrowseCategory,
        movies: List<MovieEntity>
    ) {
        deleteMoviesByCategory(category)
        insertMovies(movies)
    }
}