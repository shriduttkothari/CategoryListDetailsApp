package com.categoryapp.ui.subcategory

import android.view.View
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.SubCategory

interface SubCategoryRecyclerViewClickListener {

    fun onRecyclerViewItemClick(view: View, category: SubCategory)
}
