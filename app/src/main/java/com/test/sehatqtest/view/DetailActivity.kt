package com.test.sehatqtest

import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.sehatqtest.view.fragment.*
//import com.test.sehatqtest.view.SearchActivity

class DetailActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar

    lateinit var imageView: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvPrice: TextView
    lateinit var tvDesc: TextView
    lateinit var btnBuy: Button
    lateinit var btnShare: ImageView

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d("TAG", "onCreateeeee: ")


        //actionbar
        toolbar = supportActionBar!!
        //set actionbar title
        toolbar!!.title = "Detail"
        //set back button
        toolbar.setDisplayHomeAsUpEnabled(true)
        toolbar.setDisplayHomeAsUpEnabled(true)

        imageView = findViewById(R.id.image_detail)
        tvTitle = findViewById(R.id.title_detail)
        tvPrice = findViewById(R.id.price_detail)
        tvDesc = findViewById(R.id.desc_detail)
        btnBuy = findViewById(R.id.btnBuy)
        btnShare = findViewById(R.id.btnShare)

        val intent = intent
        val image = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val desc = intent.getStringExtra("description")

        val requestOption = RequestOptions()
        requestOption.placeholder(R.drawable.ic_launcher_background)
        requestOption.error(R.drawable.ic_launcher_background)
        Glide.with(imageView).setDefaultRequestOptions(requestOption)
            .load(image).into(imageView)

        tvTitle.text = title
        tvPrice.text = price
        tvDesc.text = desc

        btnShare.setImageResource(R.drawable.ic_share)
        btnBuy.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmation")
            builder.setMessage("Do you want to buy ?")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        Log.d("TAG", "onCreatessssssssss: " + image)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}