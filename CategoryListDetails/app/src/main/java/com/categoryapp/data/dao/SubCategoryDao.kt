package com.categoryapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.SubCategory

/**
 * @author Shridutt.Kothari
 */
@Dao
interface SubCategoryDao {

    /**
     * Get a subcategory by category id.
     * @return the subcategory from the table with a specific id.
     */
    @Query("SELECT * FROM subcategory WHERE category_id = :categoryId ORDER BY subcategory_id ASC")
    fun getSubCategoryByCategoryId(categoryId: Int): List<SubCategory>?
}