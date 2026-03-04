package com.sublime.core.database.converter

import androidx.room.TypeConverter
import com.sublime.core.database.model.MovieCategory

class MovieCategoryConverter {

    @TypeConverter
    fun fromCategory(category: MovieCategory): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(value: String): MovieCategory {
        return MovieCategory.valueOf(value)
    }
}