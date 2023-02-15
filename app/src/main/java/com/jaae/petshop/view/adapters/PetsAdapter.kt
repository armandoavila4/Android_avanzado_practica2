package com.jaae.petshop.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaae.petshop.databinding.PetElementBinding
import com.jaae.petshop.model.Pet
import com.jaae.petshop.view.activities.MainActivity

class PetsAdapter(private val context: Context, private val pets: ArrayList<Pet>): RecyclerView.Adapter<PetsAdapter.ViewHolder>() {
    class ViewHolder(view: PetElementBinding): RecyclerView.ViewHolder(view.root) {
        val ivThumbnail = view.ivPet
        val tvNamePet = view.lbPetName
        val tvSubnamePet = view.lbPetSubname
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PetElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNamePet.text = pets[position].name
        holder.tvSubnamePet.text = pets[position].subname

        Glide.with(context)
            .load(pets[position].thumbnail)
            .into(holder.ivThumbnail)

        //Para los clicks
        holder.itemView.setOnClickListener{
            if(context is MainActivity) context.selectedPet(pets[position])
        }
    }

    override fun getItemCount(): Int = pets.size
}