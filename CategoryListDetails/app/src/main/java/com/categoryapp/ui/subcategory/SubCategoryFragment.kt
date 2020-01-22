package com.categoryapp.ui.subcategory

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.categoryapp.Injection
import com.categoryapp.R
import com.categoryapp.data.model.SubCategory
import com.categoryapp.ui.DetailsActivity
import com.categoryapp.ui.SubCategoryActivity
import com.categoryapp.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_subcategory.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Subcategory fragment to show subcategories in a recyclerview
 *
 * @author Shridutt.Kothari
 */
class SubCategoryFragment : Fragment(), SubCategoryRecyclerViewClickListener {

    private lateinit var viewModelFactory: SubCategoryViewModelFactory
    private lateinit var viewModel: SubCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subcategory, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categoryId: Int= activity!!.intent.getIntExtra(AppConstants.CATEGORY_ID, 0)
        viewModelFactory = Injection.provideSubCategoryViewModelFactory(this.requireContext())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SubCategoryViewModel::class.java)

        viewModel.getSubCategories(categoryId)

        viewModel.subCategories.observe(viewLifecycleOwner, Observer { subCategoryList ->
            recycler_view_subcategory.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = SubCategoryAdapter(subCategoryList, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, subCategory: SubCategory) {
        Toast.makeText(view.context, "Not implemented", Toast.LENGTH_LONG).show();
        (activity as SubCategoryActivity?)?.let{
            val intent = Intent (it, DetailsActivity::class.java)
            intent.putExtra(AppConstants.SUB_CATEGORY_ID, subCategory.subcategoryId)
            intent.putExtra(AppConstants.SUB_CATEGORY_TITLE, subCategory.title)
            it.startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        // Inflate the menu_subcategory; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_category, menu)
        var searchMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchMenuItem!!.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :  androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //Will be called when user types something
                var adapter = recycler_view_subcategory.adapter
                var filterableAdapter: Filterable = adapter as Filterable
                var filter: Filter = filterableAdapter.filter
                filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //called when user finally submits the search query
                var adapter = recycler_view_subcategory.adapter
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
                Collections.sort(viewModel.subCategories.value, SubCategory.BY_TITLE_ASCENDING)
            } else if(which == 1) {
                //Descending
                Collections.sort(viewModel.subCategories.value, SubCategory.BY_TITLE_DESCENDING)
            }
            var adapter = recycler_view_subcategory.adapter
            if(adapter is SubCategoryAdapter) {
                adapter.updateDataSet(viewModel.subCategories.value as ArrayList<SubCategory>)
            }
        }
        alertDialogBuilder.create().show()
    }
}
