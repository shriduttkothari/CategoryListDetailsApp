package com.categoryapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.categoryapp.data.model.Category

/**
 * @author Shridutt.Kothari
 */
@Dao
interface CategoryDao {

    /**
     * Get all the categories present in database.
     * @return the list of categories from the table.
     */
    @Query("SELECT * from category ORDER BY id ASC")
    fun getAllCategories(): List<Category>?
}