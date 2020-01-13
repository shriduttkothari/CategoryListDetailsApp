package com.categoryapp.ui.category.filter

import android.widget.Filter
import com.categoryapp.data.model.Category
import com.categoryapp.ui.category.CategoryAdapter

/**
 * Custom filter class to filter the list of category based on their title
 *
 * @author Shridutt.Kothari
 */
class CategoryFilter: Filter {

    private var adapter: CategoryAdapter
    private var filterList: List<Category>

    constructor(filterList: List<Category>, adapter: CategoryAdapter) : super() {
        this.filterList = filterList
        this.adapter = adapter

    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        @Suppress("UNCHECKED_CAST")
        adapter.updateDataSet(results!!.values as ArrayList<Category>)
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var results: FilterResults = FilterResults()

        if(constraint!!.isNotEmpty()) {
            var upperCaseConstraint = constraint.toString().toUpperCase();
            var filteredCategory: ArrayList<Category> = ArrayList()
            filterList.forEach {
                if(it.title!!.toUpperCase().contains(upperCaseConstraint)) {
                    filteredCategory.add(it)
                }
            }
            results.count = filteredCategory.size
            results.values = filteredCategory
        } else {
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }
}