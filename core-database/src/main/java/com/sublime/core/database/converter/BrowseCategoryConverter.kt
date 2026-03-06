package com.sublime.core.database.converter

import androidx.room.TypeConverter
import com.sublime.core.model.BrowseCategory

class BrowseCategoryConverter {

    @TypeConverter
    fun fromCategory(category: BrowseCategory): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(value: String): BrowseCategory {
        return BrowseCategory.valueOf(value)
    }
}