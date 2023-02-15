package com.jaae.petshop.model

import com.google.gson.annotations.SerializedName

data class PetDetail(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("subname")
    var subname: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("promo")
    var promo: Boolean? = false,

    @SerializedName("age")
    var age: String? = null,

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("info")
    var info: String? = null,

    @SerializedName("flag")
    var flag: String? = null
)
