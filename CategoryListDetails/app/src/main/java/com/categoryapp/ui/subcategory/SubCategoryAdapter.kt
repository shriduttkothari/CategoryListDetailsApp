package com.categoryapp.ui.subcategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.categoryapp.R
import com.categoryapp.data.model.SubCategory
import com.categoryapp.databinding.RecyclerviewSubcategoryBinding

/**
 * @author Shridutt.Kothari
 */
class SubCategoryAdapter (
    private val subCategories: List<SubCategory>,
    private val listener: SubCategoryRecyclerViewClickListener
) : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>(){

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

    inner class SubCategoryViewHolder(
        val recyclerviewSubCategoryBinding: RecyclerviewSubcategoryBinding
    ) : RecyclerView.ViewHolder(recyclerviewSubCategoryBinding.root)

}