package com.categoryapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class Details(
    @NonNull
    @ColumnInfo(name = "details_id")
    val id: Int,

    @NonNull
    @ColumnInfo(name = "details")
    val title: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "subcategory_id")
    val subCategoryId: Int,

    @NonNull
    @ColumnInfo(name = "is_favorite")
    val isfavorite: Int) {

    companion object {
        var BY_TITLE_ASCENDING =
            Comparator<Details> { object1, object2 -> object1!!.title!!.compareTo(object2!!.title!!) }

        var BY_TITLE_DESCENDING =
            Comparator<Details> { object1, object2 -> object2!!.title!!.compareTo(object1!!.title!!) }
    }
}
