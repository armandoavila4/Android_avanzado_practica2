package com.jaae.petshop.model

import com.google.gson.annotations.SerializedName

data class Pet(
@SerializedName("id")
var id: String? = null,

@SerializedName("thumbnail")
var thumbnail: String? = null,

@SerializedName("name")
var name: String? = null,

@SerializedName("subname")
var subname: String? = null

)
