package com.test.sehatqtest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.sehatqtest.R
import com.test.sehatqtest.databinding.ItemCategoriesBinding
import com.test.sehatqtest.model.model2.Category

class HomeCategoryAdapter : RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder?>() {

    private val items = mutableListOf<Category>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCategoriesBinding>(
            inflater, R.layout.item_categories,parent, false)
        return CategoryViewHolder(binding)
    }


    fun updateListCategory(posters: List<Category>) {
        items.clear()
        items.addAll(posters)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            category = item!!
            holder.binding.tvCategories.text = this.category?.name
            val requestOption = RequestOptions()
            requestOption.placeholder(R.drawable.ic_launcher_background)
            requestOption.error(R.drawable.ic_launcher_background)
            Glide.with(holder.binding.imageCategory).setDefaultRequestOptions(requestOption)
                .load(this.category?.imageUrl).into(holder.binding.imageCategory)
        }
    }

    override fun getItemCount(): Int = items.size

    class CategoryViewHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

  

}