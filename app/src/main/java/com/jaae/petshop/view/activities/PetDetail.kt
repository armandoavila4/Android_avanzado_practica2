package com.jaae.petshop.view.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jaae.petshop.databinding.ActivityPetDetailBinding
import com.jaae.petshop.model.PetDetail
import com.jaae.petshop.network.PetsApi
import com.jaae.petshop.network.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetDetail : AppCompatActivity() {
    lateinit var binding: ActivityPetDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se obtiene el valor de la mascota seleccionada
        val bundle = intent.extras
        val id = bundle?.getString("id", "0")

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitService.getRetrofit().create(PetsApi::class.java).getPetDetail(id)
            call.enqueue(object : Callback<PetDetail> {
                override fun onResponse(call: Call<PetDetail>, response: Response<PetDetail>) {
                    binding.apply {
                        collapsingtoolbar.title = response.body()?.name
                        txtSubName.text = response.body()?.subname
                        txtPrice.text = response.body()?.price
                        txtDesc.text = response.body()?.info
                        txtAge.text = response.body()?.age
                        txtCountry.text = response.body()?.country

                        val id: Int = this@PetDetail.getResources()
                            .getIdentifier("flag_${response.body()?.flag}", "drawable", this@PetDetail.getPackageName())
                        ivFlag.setImageResource(id)

                        Glide.with(this@PetDetail)
                            .load(response.body()?.image)
                            .into(ivPet)

                        if(response.body()?.promo == true){
                            icDiscount.visibility = View.VISIBLE
                        }else{
                            icDiscount.visibility = View.INVISIBLE
                        }

                        pbConexion.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<PetDetail>, t: Throwable) {
                    Toast.makeText(this@PetDetail, "No hay conexión. Error al obtener la información.", Toast.LENGTH_SHORT)
                        .show()
                    binding.pbConexion.visibility = View.INVISIBLE
                }
            })
        }

    }
}