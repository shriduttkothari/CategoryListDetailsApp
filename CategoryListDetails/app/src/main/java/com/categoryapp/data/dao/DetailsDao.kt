package com.categoryapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.Details

/**
 * @author Shridutt.Kothari
 */
@Dao
interface DetailsDao {

    /**
     * Get the details present in database for the given subcategoryId.
     * @return the details from the table.
     */
    @Query("SELECT * from details WHERE subcategory_id =:subCategoryId")
    fun getDetails(subCategoryId: Int): Details?
}