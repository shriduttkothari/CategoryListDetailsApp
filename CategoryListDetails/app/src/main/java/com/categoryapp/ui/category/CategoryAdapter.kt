package com.categoryapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.categoryapp.R
import com.categoryapp.data.model.Category
import com.categoryapp.databinding.RecyclerviewCategoryBinding
import com.categoryapp.ui.category.filter.CategoryFilter
import java.util.ArrayList

/**
 * Adapter to provide categories to the recyclerview
 *
 * @author Shridutt.Kothari
 */
class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>, Filterable {

    private var categories: List<Category>
    private val filterList: List<Category>
    private val listener: CategoryRecyclerViewClickListener
    private var categoryFilter: CategoryFilter? = null

    constructor(categories: List<Category>, listener: CategoryRecyclerViewClickListener): super() {
        this.categories = categories
        this.filterList = categories
        this.listener = listener;
    }

    override fun getItemCount() = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_category,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.recyclerviewCategoryBinding.category = categories[position]

        holder.recyclerviewCategoryBinding.categoryCardView.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewCategoryBinding.categoryCardView, categories[position])
        }
    }

    fun updateDataSet(categories: ArrayList<Category>) {
        this.categories = categories
        this.notifyDataSetChanged()
    }

    inner class CategoryViewHolder(
        val recyclerviewCategoryBinding: RecyclerviewCategoryBinding
    ) : RecyclerView.ViewHolder(recyclerviewCategoryBinding.root)

    override fun getFilter(): Filter {
        if(categoryFilter == null) {
            categoryFilter = CategoryFilter(filterList, this)
        }
        return categoryFilter as Filter
    }

}