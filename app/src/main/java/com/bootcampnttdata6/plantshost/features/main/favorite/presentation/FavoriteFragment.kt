package com.bootcampnttdata6.plantshost.features.main.favorite.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bootcampnttdata6.plantshost.databinding.FragmentFavoriteBinding
import com.bootcampnttdata6.plantshost.features.main.favorite.presentation.adapter.FavoriteAdapter
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding:FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: FavoriteViewModel

    private lateinit var favoriteAdapter: FavoriteAdapter
    private val listaFavoritePlants = mutableListOf<Plants>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[FavoriteViewModel::class.java]
            }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPlantsRecyclerview()
        getListTable()
    }

    private fun getListTable() {
        viewModel.listPlants.observe(viewLifecycleOwner) {
            favoriteAdapter.setItems(it)
        }
    }

    private fun initPlantsRecyclerview() {
        binding.rvFavorite.layoutManager = GridLayoutManager(activity,1, RecyclerView.VERTICAL,false)
        favoriteAdapter = FavoriteAdapter(listaFavoritePlants) {
                dataPlants -> onClickListener(dataPlants)
        }
        binding.rvFavorite.adapter = favoriteAdapter
    }

    private fun onClickListener(dataPlants: Plants) {
        val action = FavoriteFragmentDirections.actionFavoriteToDetail(dataPlants.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}