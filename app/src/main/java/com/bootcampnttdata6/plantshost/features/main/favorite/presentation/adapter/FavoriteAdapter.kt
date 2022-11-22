package com.bootcampnttdata6.plantshost.features.main.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.ItemFavoriteBinding
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FavoriteAdapter (
    private val data: MutableList<Plants>,
    private val onClickListener: (Plants) -> Unit)
: RecyclerView.Adapter<FavoriteAdapter.holderPlanst>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderPlanst {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderPlanst(layoutInflater.inflate(R.layout.item_favorite,parent,false))
    }

    override fun onBindViewHolder(holder: holderPlanst, position: Int) {
        holder.render(data[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class holderPlanst(private val view: View): RecyclerView.ViewHolder(view){
        val binding = ItemFavoriteBinding.bind(view)
        fun render (data: Plants, onClickListener: (Plants) -> Unit){
            Glide
                .with(this.view)
                .load(data.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivFavoritePlants)

            binding.tvPlants.text = data.nameplant.uppercase()

            itemView.setOnClickListener { onClickListener(data) }
        }
    }

    fun setItems(list: MutableList<Plants>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}