package com.test.sehatqtest.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.sehatqtest.R
import com.test.sehatqtest.databinding.ItemProductBinding
import com.test.sehatqtest.model.model2.ProductPromo


class HomeProductAdapter(private val mListener: (ProductPromo) -> Unit) : RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder?>() {

    private val items = mutableListOf<ProductPromo>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            inflater, R.layout.item_product, parent, false
        )
        return ProductViewHolder(binding)
    }

    fun updateListProduct(posters: List<ProductPromo>) {
        items.clear()
        items.addAll(posters)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            productPromo = item
            holder.binding.tvProduct.text = this.productPromo?.title
            val requestOption = RequestOptions()
            requestOption.placeholder(R.drawable.ic_launcher_background)
            requestOption.error(R.drawable.ic_launcher_background)
            Glide.with(holder.binding.imageProduct).setDefaultRequestOptions(requestOption)
                .load(this.productPromo?.imageUrl).into(holder.binding.imageProduct)

            holder.itemView.setOnClickListener {
                mListener(item)
            }

        }


    }

    override fun getItemCount(): Int = items.size

    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}