package com.categoryapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.categoryapp.data.dao.DetailsDao
import com.categoryapp.data.model.Details
import com.categoryapp.utils.Coroutines
import kotlinx.coroutines.Job

/**
 * Category View Model
 *
 * @author Shridutt.Kothari
 */
class DetailsViewModel(private val detailsDao: DetailsDao) : ViewModel() {

    private lateinit var job: Job

    private val _details = MutableLiveData<Details>()
    val details: LiveData<Details> get() = _details

    fun getDetails(subCategoryId: Int) {
        job = Coroutines.ioThenMain(
            { detailsDao.getDetails(subCategoryId)},
            { _details.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}