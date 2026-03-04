package com.sublime.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sublime.core.database.entity.WatchedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchedDao {

    @Query("SELECT * FROM watched_movies")
    fun observeWatched(): Flow<List<WatchedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatched(entity: WatchedEntity)

    @Query("DELETE FROM watched_movies WHERE movieId = :movieId")
    suspend fun deleteWatched(movieId: Long)
}