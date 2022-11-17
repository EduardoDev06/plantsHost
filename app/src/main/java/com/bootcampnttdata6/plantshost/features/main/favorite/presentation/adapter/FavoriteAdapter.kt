package com.bootcampnttdata6.plantshost.features.main.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.CarviewFavoriteBinding
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.model.PlantModel
import com.bumptech.glide.Glide

class FavoriteAdapter (
    private val data: MutableList<PlantModel>,
    private val onClickListener: (PlantModel) -> Unit)
: RecyclerView.Adapter<FavoriteAdapter.holderPlanst>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderPlanst {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderPlanst(layoutInflater.inflate(R.layout.carview_favorite,parent,false))
    }

    override fun onBindViewHolder(holder: holderPlanst, position: Int) {
        holder.render(data[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class holderPlanst(private val view: View): RecyclerView.ViewHolder(view){
        val binding = CarviewFavoriteBinding.bind(view)
        fun render (data: PlantModel, onClickListener: (PlantModel) -> Unit){
            Glide
                .with(this.view)
                .load(data.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivFavoritePlants)
            binding.tvPlants.text = data.nameplant
        }
    }

    fun setItems(list: MutableList<PlantModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}