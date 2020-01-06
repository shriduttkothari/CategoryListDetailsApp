package com.categoryapp.ui.category

import android.view.View
import com.categoryapp.data.model.Category

interface CategoryRecyclerViewClickListener {

    fun onRecyclerViewItemClick(view: View, category: Category)
}
