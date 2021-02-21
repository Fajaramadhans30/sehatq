package com.test.sehatqtest.model.model2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category :Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}