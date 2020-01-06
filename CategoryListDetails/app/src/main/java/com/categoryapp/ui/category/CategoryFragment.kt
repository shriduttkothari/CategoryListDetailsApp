package com.categoryapp.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.categoryapp.Injection
import com.categoryapp.R
import com.categoryapp.data.model.Category
import com.categoryapp.ui.CategoryActivity
import com.categoryapp.ui.SubCategoryActivity
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * @author Shridutt.Kothari
 */
class CategoryFragment : Fragment(), CategoryRecyclerViewClickListener {

    private lateinit var viewModelFactory: CategoryViewModelFactory
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = Injection.provideCategoryViewModelFactory(this.requireContext())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoryViewModel::class.java)

        viewModel.getCategories()

        viewModel.categories.observe(viewLifecycleOwner, Observer { categoryList ->
            recycler_view_category.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CategoryAdapter(categoryList, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, category: Category) {
        //Toast.makeText(view.context, "Not implemented", Toast.LENGTH_LONG).show();
        (activity as CategoryActivity?)?.let{
            val intent = Intent (it, SubCategoryActivity::class.java)
            intent.putExtra("categoryId", category.id)
            it.startActivity(intent)
        }
    }
}
