package com.categoryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.categoryapp.data.dao.CategoryDao
import com.categoryapp.data.dao.SubCategoryDao
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.SubCategory

@Database(entities = [Category::class, SubCategory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun subCategoryDao(): SubCategoryDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null
        @Volatile private var DATABASE_NAME: String = "app.db"


        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .createFromAsset(DATABASE_NAME)
                .build()
    }
}