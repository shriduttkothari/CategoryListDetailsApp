package com.categoryapp

import android.content.Context
import com.categoryapp.data.dao.CategoryDao
import com.categoryapp.data.dao.SubCategoryDao
import com.categoryapp.data.local.AppDatabase
import com.categoryapp.ui.category.CategoryViewModelFactory
import com.categoryapp.ui.subcategory.SubCategoryViewModelFactory

/**
 * Enables injection of data sources.
 */
object Injection {

    fun provideCategoryViewModelFactory(context: Context): CategoryViewModelFactory {
        val dataSource = provideCategoryDataSource(context)
        return CategoryViewModelFactory(dataSource)
    }

    private fun provideCategoryDataSource(context: Context): CategoryDao {
        val database = AppDatabase.getInstance(context)
        return database.categoryDao()
    }

    fun provideSubCategoryViewModelFactory(context: Context): SubCategoryViewModelFactory {
        val dataSource = provideSubCategoryDataSource(context)
        return SubCategoryViewModelFactory(dataSource)
    }

    private fun provideSubCategoryDataSource(context: Context): SubCategoryDao {
        val database = AppDatabase.getInstance(context)
        return database.subCategoryDao()
    }

}