package com.categoryapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.categoryapp.R
import com.categoryapp.utils.AppConstants

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        title = intent.getStringExtra(AppConstants.SUB_CATEGORY_TITLE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}