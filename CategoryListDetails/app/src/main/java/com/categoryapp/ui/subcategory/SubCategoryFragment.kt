package com.categoryapp.ui.subcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.categoryapp.Injection
import com.categoryapp.R
import com.categoryapp.data.model.SubCategory
import kotlinx.android.synthetic.main.fragment_subcategory.*

/**
 * @author Shridutt.Kothari
 */
class SubCategoryFragment : Fragment(), SubCategoryRecyclerViewClickListener {

    private lateinit var viewModelFactory: SubCategoryViewModelFactory
    private lateinit var viewModel: SubCategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subcategory, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val catgoryId: Int= activity!!.intent.getIntExtra("categoryId", 0)
        //val catgoryId: Int= arguments!!.getInt("categoryId")
        viewModelFactory = Injection.provideSubCategoryViewModelFactory(this.requireContext())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SubCategoryViewModel::class.java)

        viewModel.getSubCategories(catgoryId)

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
//        (activity as SubCategoryActivity?)?.let{
//            val intent = Intent (it, DetailActivity::class.java)
//            it.startActivity(intent)
//        }
    }
}
