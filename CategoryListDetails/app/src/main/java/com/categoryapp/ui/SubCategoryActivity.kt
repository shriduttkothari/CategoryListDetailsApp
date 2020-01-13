package com.categoryapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.categoryapp.R
import com.categoryapp.utils.AppConstants

/**
 * Subcategory Activity
 *
 * @author Shridutt.Kothari
 */
class SubCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        title = intent.getStringExtra(AppConstants.CATEGORY_TITLE);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}