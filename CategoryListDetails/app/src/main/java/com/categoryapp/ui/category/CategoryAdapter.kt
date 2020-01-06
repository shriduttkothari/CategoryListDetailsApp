package com.categoryapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.categoryapp.R
import com.categoryapp.data.model.Category
import com.categoryapp.databinding.RecyclerviewCategoryBinding

/**
 * @author Shridutt.Kothari
 */
class CategoryAdapter (
    private val categories: List<Category>,
    private val listener: CategoryRecyclerViewClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

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

    inner class CategoryViewHolder(
        val recyclerviewCategoryBinding: RecyclerviewCategoryBinding
    ) : RecyclerView.ViewHolder(recyclerviewCategoryBinding.root)

}