package com.categoryapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.categoryapp.data.dao.CategoryDao
import com.categoryapp.data.dao.DetailsDao

/**
 * @author Shridutt.Kothari
 */
@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val dataSource: DetailsDao) : ViewModelProvider.Factory{

    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}