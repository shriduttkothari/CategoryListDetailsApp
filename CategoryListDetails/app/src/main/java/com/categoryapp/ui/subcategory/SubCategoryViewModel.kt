package com.categoryapp.ui.subcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.categoryapp.data.dao.CategoryDao
import com.categoryapp.data.dao.SubCategoryDao
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.SubCategory
import com.categoryapp.utils.Coroutines
import kotlinx.coroutines.Job

/**
 *
 * @author Shridutt.Kothari
 */
class SubCategoryViewModel(private val subCategoryDao: SubCategoryDao) : ViewModel() {

    private lateinit var job: Job

    private val _subCategory = MutableLiveData<List<SubCategory>>()
    val subCategories: LiveData<List<SubCategory>> get() = _subCategory

    fun getSubCategories(categoryId: Int) {
        job = Coroutines.ioThenMain(
            { subCategoryDao.getSubCategoryByCategoryId(categoryId)},
            { _subCategory.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}