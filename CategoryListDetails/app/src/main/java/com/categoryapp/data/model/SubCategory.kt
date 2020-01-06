package com.categoryapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.UNSPECIFIED
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subcategory")
data class SubCategory(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "subcategory_id")
    val subcategoryId: Int,

    @NonNull
    @ColumnInfo(name = "category_id")
    val categoryId: Int,

    @NonNull
    @ColumnInfo(name = "title")
    val title: String,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Int){
}

