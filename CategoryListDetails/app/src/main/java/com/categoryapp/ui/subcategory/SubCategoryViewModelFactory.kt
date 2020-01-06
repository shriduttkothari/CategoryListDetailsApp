package com.categoryapp.ui.subcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.categoryapp.data.dao.CategoryDao
import com.categoryapp.data.dao.SubCategoryDao

/**
 * @author Shridutt.Kothari
 */
@Suppress("UNCHECKED_CAST")
class SubCategoryViewModelFactory(private val dataSource: SubCategoryDao) : ViewModelProvider.Factory{

    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubCategoryViewModel::class.java)) {
            return SubCategoryViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}