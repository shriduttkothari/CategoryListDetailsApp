package com.categoryapp.ui.subcategory

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.categoryapp.R
import com.categoryapp.data.model.Category
import com.categoryapp.data.model.SubCategory
import com.categoryapp.databinding.RecyclerviewSubcategoryBinding
import com.categoryapp.ui.category.CategoryAdapter
import com.categoryapp.ui.category.CategoryRecyclerViewClickListener
import com.categoryapp.ui.category.filter.CategoryFilter
import com.categoryapp.ui.category.filter.SubCategoryFilter

/**
 * Adapter to provide subcategories to the recyclerview
 *
 * @author Shridutt.Kothari
 */
class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>, Filterable {

    private var subCategories: List<SubCategory>
    private val filterList: List<SubCategory>
    private val listener: SubCategoryRecyclerViewClickListener
    private var subCategoryFilter: SubCategoryFilter? = null

    constructor(subCategories: List<SubCategory>, listener: SubCategoryRecyclerViewClickListener): super() {
        this.subCategories = subCategories
        this.filterList = subCategories
        this.listener = listener;
    }

    override fun getItemCount() = subCategories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SubCategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_subcategory,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.recyclerviewSubCategoryBinding.subCategory = subCategories[position]

        holder.recyclerviewSubCategoryBinding.subCategoryCardView.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewSubCategoryBinding.subCategoryCardView, subCategories[position])
        }
    }

    fun updateDataSet(subCategories: ArrayList<SubCategory>) {
        this.subCategories = subCategories
        this.notifyDataSetChanged()
    }

    inner class SubCategoryViewHolder(
        val recyclerviewSubCategoryBinding: RecyclerviewSubcategoryBinding
    ) : RecyclerView.ViewHolder(recyclerviewSubCategoryBinding.root)

    override fun getFilter(): Filter {
        if(subCategoryFilter == null) {
            subCategoryFilter = SubCategoryFilter(filterList, this)
        }
        return subCategoryFilter as Filter
    }
}