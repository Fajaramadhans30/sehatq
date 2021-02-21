package com.test.sehatqtest.model.model2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.sehatqtest.model.model2.Datum
import java.io.Serializable


class Data : Serializable {
    @SerializedName("data")
    @Expose
    val datum: Datum? = null
}