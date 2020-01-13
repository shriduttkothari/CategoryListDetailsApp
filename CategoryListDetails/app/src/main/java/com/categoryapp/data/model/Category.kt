package com.categoryapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @NonNull
    @ColumnInfo(name = "title")
    val title: String) {

    companion object {
        var BY_TITLE_ASCENDING =
            Comparator<Category> { object1, object2 -> object1!!.title!!.compareTo(object2!!.title!!) }

        var BY_TITLE_DESCENDING =
            Comparator<Category> { object1, object2 -> object2!!.title!!.compareTo(object1!!.title!!) }
    }
}
