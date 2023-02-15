package com.jaae.petshop.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaae.petshop.databinding.ActivityMainBinding
import com.jaae.petshop.model.Pet
import com.jaae.petshop.network.PetsApi
import com.jaae.petshop.network.RetrofitService
import com.jaae.petshop.utils.Constants
import com.jaae.petshop.view.adapters.PetsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitService.getRetrofit().create(PetsApi::class.java)
                    .getPets("pets")

            call.enqueue(object : Callback<ArrayList<Pet>> {
                override fun onResponse(
                    call: Call<ArrayList<Pet>>,
                    response: Response<ArrayList<Pet>>
                ) {
                    binding.pbConexion.visibility = View.GONE
                    binding.rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvPets.adapter = PetsAdapter(this@MainActivity, response.body()!!)

                }

                override fun onFailure(call: Call<ArrayList<Pet>>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "No hay conexión. Error al obtener la información.",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.pbConexion.visibility = View.GONE
                }
            })
        }
    }

    fun selectedPet(pet: Pet) {
        val parametros = Bundle().apply {
            putString("id", pet.id)
        }

        val intent = Intent(this, PetDetail::class.java).apply {
            putExtras(parametros)
        }

        startActivity(intent)
    }
}