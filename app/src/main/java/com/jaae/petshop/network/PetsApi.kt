package com.jaae.petshop.network

import com.jaae.petshop.model.Pet
import com.jaae.petshop.model.PetDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PetsApi {
    @GET
    fun getPets(
        @Url url: String?
    ): Call<ArrayList<Pet>>

    @GET("pets/pet_detail/{id}")
    fun getPetDetail(
        @Path("id") id: String?
    ): Call<PetDetail>

}