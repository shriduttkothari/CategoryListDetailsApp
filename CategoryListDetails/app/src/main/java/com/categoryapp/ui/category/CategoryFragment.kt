package com.categoryapp.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.categoryapp.Injection
import com.categoryapp.R
import com.categoryapp.data.model.Category
import com.categoryapp.ui.CategoryActivity
import com.categoryapp.ui.SubCategoryActivity
import com.categoryapp.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_category.*
import java.util.*

/**
 * Category Fragment to show categories in a recyclerview
 *
 * @author Shridutt.Kothari
 */
class CategoryFragment : Fragment(), CategoryRecyclerViewClickListener {

    private lateinit var viewModelFactory: CategoryViewModelFactory
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

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
        (activity as CategoryActivity?)?.let{
            val intent = Intent (it, SubCategoryActivity::class.java)
            intent.putExtra(AppConstants.CATEGORY_ID, category.id)
            intent.putExtra(AppConstants.CATEGORY_TITLE, category.title)
            it.startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        // Inflate the menu_category; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_category, menu)
        var searchMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchMenuItem!!.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :  androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //Will be called when user types something
                var adapter = recycler_view_category.adapter
                var mFilterableAdapter: Filterable = adapter as Filterable
                var filter: Filter = mFilterableAdapter.filter
                filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //called when user finally submits the search query
                var adapter = recycler_view_category.adapter
                var filterableAdapter: Filterable = adapter as Filterable
                var filter: Filter = filterableAdapter.filter
                filter.filter(query)
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            R.id.action_sort -> {
                showSortDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSortDialog() {
        val options: Array<String> = arrayOf(getString(R.string.ascending) + getString(R.string.title), getString(R.string.descending) + getString(R.string.title));
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext() , R.style.MyDialogTheme)
        alertDialogBuilder.setTitle(R.string.sort_by)
        alertDialogBuilder.setItems(options) { dialog, which ->
            if(which == 0) {
                //Ascending
                Collections.sort(viewModel.categories.value, Category.BY_TITLE_ASCENDING)
            } else if(which == 1) {
                //Descending
                Collections.sort(viewModel.categories.value, Category.BY_TITLE_DESCENDING)
            }
            var adapter = recycler_view_category.adapter
            if(adapter is CategoryAdapter) {
                adapter.updateDataSet(viewModel.categories.value as ArrayList<Category>)
            }
        }
        alertDialogBuilder.create().show()
    }
}
