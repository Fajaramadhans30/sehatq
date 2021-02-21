package com.test.sehatqtest.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.whatif.whatIfNotNullOrEmpty
import com.test.sehatqtest.DetailActivity
import com.test.sehatqtest.MainActivity
import com.test.sehatqtest.R
import com.test.sehatqtest.api.Provider
import com.test.sehatqtest.databinding.FragmentHomeBinding
import com.test.sehatqtest.model.model2.Category
import com.test.sehatqtest.model.model2.Data
import com.test.sehatqtest.model.model2.ProductPromo
import com.test.sehatqtest.view.adapter.HomeCategoryAdapter
import com.test.sehatqtest.view.adapter.HomeProductAdapter
import com.test.sehatqtest.viewmodel.HomeCategoryViewModel
import com.test.sehatqtest.viewmodel.HomeProductViewModel
import com.test.sehatqtest.viewmodel.ViewModelCategoryFactory
import com.test.sehatqtest.viewmodel.ViewModelProductFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeFragment : Fragment() {
    private lateinit var homeCategoryAdapter: HomeCategoryAdapter
    private lateinit var homeProductAdapter: HomeProductAdapter

    private lateinit var homeCategoryViewModel: HomeCategoryViewModel
    private lateinit var homeProductViewModel: HomeProductViewModel

    private var dataList: MutableList<Data?> = mutableListOf()
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val compositeDisposable = CompositeDisposable()
    private val repository = Provider.dataProviderRepository()

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        super.onCreate(savedInstanceState)

//        swpContact.onRefresh {
//            swpContact.isRefreshing = true
//            contactViewModel.setListContact()
//            contactViewModel.getListContact().observe(this, getContact)
//        }
        val view = binding.root
        return view
//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rv1.layoutManager = linearLayoutManager
        binding.rv1.hasFixedSize()
        homeCategoryAdapter = HomeCategoryAdapter()
        binding.rv1.adapter = homeCategoryAdapter

        homeCategoryViewModel = ViewModelProviders.of(
            this,
            ViewModelCategoryFactory(
                compositeDisposable,
                repository,
                AndroidSchedulers.mainThread(),
                Schedulers.io()
            )
        ).get(HomeCategoryViewModel::class.java)
        homeCategoryViewModel.setListCategory()
        homeCategoryViewModel.getListCategory().observe(viewLifecycleOwner, getCategory)


        //Product
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rv2.layoutManager = linearLayoutManager
        binding.rv2.hasFixedSize()
        homeProductAdapter = HomeProductAdapter() {
            val intentDetail = Intent(context, DetailActivity::class.java)
            intentDetail.putExtra(getString(R.string.intent_title), it.title)
            intentDetail.putExtra(getString(R.string.intent_image), it.imageUrl)
            intentDetail.putExtra(getString(R.string.price), it.price)
            intentDetail.putExtra(getString(R.string.intent_desc), it.description)
            startActivity(intentDetail)

            Log.d("TAG", "onViewCreateddddd: " + intentDetail)
        }
        binding.rv2.adapter = homeProductAdapter

        homeProductViewModel = ViewModelProviders.of(
            this,
            ViewModelProductFactory(
                compositeDisposable,
                repository,
                AndroidSchedulers.mainThread(),
                Schedulers.io()
            )
        ).get(HomeProductViewModel::class.java)
        homeProductViewModel.setListProduct()
        homeProductViewModel.getListProduct().observe(viewLifecycleOwner, getProductPromo)
    }

    private val getCategory = Observer<MutableList<Category>> { categoryItem ->
        if (categoryItem != null) {
            dataList.clear()
            binding.rv1.visibility = View.VISIBLE
//            idLoading.visibility = View.GONE
//            idError.visibility = View.GONE
//            if (contactItems.size > 0) {
//                val dataListCategory: MutableList<Category?> = mutableListOf()
//                contactItems.let { dataListCategory.addAll(it) }
//                homeCategoryAdapter.updateListCategory(dataListCategory)
//            }
            categoryItem.whatIfNotNullOrEmpty {
                (homeCategoryAdapter as? HomeCategoryAdapter)?.updateListCategory(it)
            }

//            if (contactItems.size > 0) {
//                val datalistMovieNew: MutableList<Category?> = mutableListOf()
//                contactItems.let { datalistMovieNew.addAll(it) }
//                homeCategoryAdapter.addData(datalistMovieNew)
//            }
        } else {
            binding.rv1.visibility = View.GONE
//            idLoading.visibility = View.GONE
//            idError.visibility = View.VISIBLE
        }
//        swpContact.isRefreshing = false
    }

    private val getProductPromo = Observer<MutableList<ProductPromo>> { productItem ->
        if (productItem != null) {
            dataList.clear()
            binding.rv2.visibility = View.VISIBLE
//            idLoading.visibility = View.GONE
//            idError.visibility = View.GONE
//            if (contactItems.size > 0) {
//                val dataListCategory: MutableList<Category?> = mutableListOf()
//                contactItems.let { dataListCategory.addAll(it) }
//                homeCategoryAdapter.updateListCategory(dataListCategory)
//            }
            productItem.whatIfNotNullOrEmpty {
                (homeProductAdapter as? HomeProductAdapter)?.updateListProduct(it)
            }

//            if (contactItems.size > 0) {
//                val datalistMovieNew: MutableList<Category?> = mutableListOf()
//                contactItems.let { datalistMovieNew.addAll(it) }
//                homeCategoryAdapter.addData(datalistMovieNew)
//            }
        } else {
            binding.rv2.visibility = View.GONE
//            idLoading.visibility = View.GONE
//            idError.visibility = View.VISIBLE
        }
//        swpContact.isRefreshing = false
    }


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}