package com.categoryapp.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.categoryapp.data.dao.CategoryDao
import com.categoryapp.data.model.Category
import com.categoryapp.utils.Coroutines
import kotlinx.coroutines.Job

/**
 *
 * @author Shridutt.Kothari
 */
class CategoryViewModel(private val categoryDao: CategoryDao) : ViewModel() {

    private lateinit var job: Job

    private val _category = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _category

    fun getCategories() {
        job = Coroutines.ioThenMain(
            { categoryDao.getAllCategories()},
            { _category.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}