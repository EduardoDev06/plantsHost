package com.bootcampnttdata6.plantshost.features.main.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bootcampnttdata6.plantshost.databinding.ItemPlantsBinding
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PlantsAdapter(private val listener: OnClickListener) :
    ListAdapter<Plants, PlantsAdapter.ViewHolder>(DiffUtilCallback()) {

    interface OnClickListener {
        fun onClick(resultPlants: Plants)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemPlantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val resultPlants = getItem(position)
            bind(resultPlants)
        }
    }

    inner class ViewHolder(private val binding: ItemPlantsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(resultPlants: Plants) = with(binding) {
            ivplants.setOnClickListener {
                listener.onClick(resultPlants)
            }
            Glide.with(ivplants.context).load(resultPlants.image)
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).centerCrop().into(ivplants)
            name.text = resultPlants.nameplant
            description.text = resultPlants.description
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Plants>() {
        override fun areItemsTheSame(oldItem: Plants, newItem: Plants): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Plants, newItem: Plants): Boolean =
            oldItem == newItem
    }


}