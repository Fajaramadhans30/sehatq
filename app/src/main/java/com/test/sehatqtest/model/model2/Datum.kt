package com.test.sehatqtest.model.model2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.sehatqtest.model.model2.Category

class Datum {
    @SerializedName("category")
    @Expose
    var categoryData: List<Category>? = null

    @SerializedName("productPromo")
    @Expose
    var productPromoData: List<ProductPromo>? = null
}