package com.test.sehatqtest.model.model2

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


//class ProductPromo : Serializable {
//    @SerializedName("id")
//    @Expose
//    var id: Int? = null
//
//    @SerializedName("imageUrl")
//    @Expose
//    var imageUrl: String? = null
//
//    @SerializedName("title")
//    @Expose
//    var title: String? = null
//
//    @SerializedName("description")
//    @Expose
//    var description: String? = null
//
//    @SerializedName("price")
//    @Expose
//    var price: String? = null
//
//    @SerializedName("loved")
//    @Expose
//    var loved: Int? = null
//}

//@SuppressLint("ParcelCreator")
//@Entity
//abstract class ProductPromo(
//    @PrimaryKey val id: String,
//    val imageUrl: String,
//    val title: String,
//    val price: String,
//    val loved:Int
//) : Parcelable

class ProductPromo : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("loved")
    @Expose
    var loved: Int? = null
}

