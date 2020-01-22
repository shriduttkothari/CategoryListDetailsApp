package com.categoryapp.ui.details

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
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.*

/**
 * Details fragment to show details of a given subcategory
 *
 * @author Shridutt.Kothari
 */
class DetailsFragment : Fragment() {

    private lateinit var viewModelFactory: DetailsViewModelFactory
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val subCategoryId = activity!!.intent!!.getIntExtra(AppConstants.SUB_CATEGORY_ID, 0)

        viewModelFactory = Injection.provideDetailsViewModelFactory(this.requireContext())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)

        viewModel.getDetails(subCategoryId)

        viewModel.details.observe(viewLifecycleOwner, Observer { details->
            // Refresh or Set the UI data from here
            detailsTextView.text = details?.title
        })
    }
}
