package com.bootcampnttdata6.plantshost.features.main.detail.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val num by navArgs<DetailFragmentArgs>()
    lateinit var viewModel: DetailViewModel
    var isFav: Boolean = false
    var currentId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[DetailViewModel::class.java]
            }!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch { eventListeners() }
        initComponent()
    }

    private fun initComponent() {
        getDataNavigation()
        getDataPlant()
    }

    private fun eventListeners() {
        binding.favbutton.setOnClickListener{
            isFav = if (!isFav) {
                binding.favbutton.setImageResource(R.drawable.ic_favorite_red)
                viewModel.changeFav(currentId,true)
                true
            } else {
                binding.favbutton.setImageResource(R.drawable.ic_favorite)
                viewModel.changeFav(currentId, false)
                false
            }
        }
    }

    private fun getDataNavigation() {
        viewModel.getDataPlant(num.idPlant)
    }

    private fun getDataPlant() {
        viewModel.showDataPlant.observe(viewLifecycleOwner){plants ->
            isFav = plants.isFavorite
            currentId = plants.id
            binding.showName.text=plants.nameplant
            binding.showPrice.text=plants.price
            binding.showDescription.text=plants.description
            binding.showStatus.text=plants.status
            Glide
                .with(this)
                .load(plants.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.showPic)
            if (isFav) {
                binding.favbutton.setImageResource(R.drawable.ic_favorite_red)}
            else {
                binding.favbutton.setImageResource(R.drawable.ic_favorite)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}