package com.bootcampnttdata6.plantshost.features.main.home.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bootcampnttdata6.plantshost.databinding.ItemPlantsBinding
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class PlantsAdapter(private val onItemClick: (Plants) -> Unit) :
    ListAdapter<Plants, PlantsAdapter.ViewHolder>(DiffUtilCallback()), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemPlantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val itemPlant = getItem(position)
            bind(itemPlant)
        }
    }

    inner class ViewHolder(private val binding: ItemPlantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemPlant: Plants) = with(binding) {
            itemView.setOnClickListener {
                onItemClick(itemPlant)
            }
            val shimmer = Shimmer.ColorHighlightBuilder()
                .setBaseColor(
                    ContextCompat.getColor(
                        ivplants.context,
                        android.R.color.darker_gray
                    )
                )
                .setBaseAlpha(1.0f)
                .setHighlightAlpha(0.1f)
                .setHighlightColor(
                    ContextCompat.getColor(
                        ivplants.context,
                        android.R.color.system_neutral1_200
                    )
                )
                .setDuration(800)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()
            val shimmerDrawable = ShimmerDrawable()
            shimmerDrawable.setShimmer(shimmer)
            Glide.with(ivplants.context).load(itemPlant.image)
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).centerCrop().placeholder(shimmerDrawable).into(ivplants)
            name.text = itemPlant.nameplant
            price.text = itemPlant.price
        }
    }

    private var completeItemList : MutableList<Plants>? = mutableListOf()


    class DiffUtilCallback : DiffUtil.ItemCallback<Plants>() {
        override fun areItemsTheSame(oldItem: Plants, newItem: Plants): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Plants, newItem: Plants): Boolean =
            oldItem == newItem
    }

    fun setData(list: MutableList<Plants>){
        this.completeItemList = list
        submitList(list)
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(query: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Plants>()
            if (query.isNullOrBlank()) {
                completeItemList?.let { filteredList.addAll(it) }
            } else {
                for (item in completeItemList!!) {
                    if (item.nameplant.contains(query, true)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults) {
            submitList(filterResults.values as MutableList<Plants>)
        }

    }

}