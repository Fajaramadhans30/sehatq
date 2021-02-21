package com.test.sehatqtest.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.sehatqtest.R

class DetailFragment(title: String?) : Fragment() {
    var titles: String? = title
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.activity_detail, container, false)


    companion object {
        fun newInstance(titles: String?): DetailFragment = DetailFragment(titles)
    }
}