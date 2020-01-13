package com.categoryapp.ui.category.filter

import android.widget.Filter
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.SubCategory
import com.categoryapp.ui.category.CategoryAdapter
import com.categoryapp.ui.subcategory.SubCategoryAdapter

/**
 * Custom filter class to filter the list of category based on their title
 *
 * @author Shridutt.Kothari
 */
class SubCategoryFilter: Filter {

    private var adapter: SubCategoryAdapter
    private var filterList: List<SubCategory>

    constructor(filterList: List<SubCategory>, adapter: SubCategoryAdapter) : super() {
        this.filterList = filterList
        this.adapter = adapter

    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        @Suppress("UNCHECKED_CAST")
        adapter.updateDataSet(results!!.values as ArrayList<SubCategory>)
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var results: FilterResults = FilterResults()

        if(constraint!!.isNotEmpty()) {
            var upperCaseConstraint = constraint.toString().toUpperCase();
            var filteredSubCategory: ArrayList<SubCategory> = ArrayList()
            filterList.forEach {
                if(it.title!!.toUpperCase().contains(upperCaseConstraint)) {
                    filteredSubCategory.add(it)
                }
            }
            results.count = filteredSubCategory.size
            results.values = filteredSubCategory
        } else {
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }
}