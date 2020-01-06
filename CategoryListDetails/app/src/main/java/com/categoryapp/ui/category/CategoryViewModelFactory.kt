package com.categoryapp.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.categoryapp.data.dao.CategoryDao

/**
 * @author Shridutt.Kothari
 */
@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory(private val dataSource: CategoryDao) : ViewModelProvider.Factory{

    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}