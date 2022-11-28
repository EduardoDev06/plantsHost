package com.bootcampnttdata6.plantshost.features.main.home.presenter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcampnttdata6.plantshost.databinding.FragmentHomeBinding
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.features.main.home.presenter.adapter.PlantsAdapter
import com.bootcampnttdata6.plantshost.features.main.home.presenter.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PlantsAdapter.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val plantsAdapter = PlantsAdapter(this)
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(layoutInflater).apply { _binding = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.fetchListPlants()
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        binding.rvPlants.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = plantsAdapter
        }
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { state ->
                    when (state) {
                        is HomeViewModel.State.Success -> {
                            state.resultPlants.let { data ->
                                if (data.isNotEmpty()) {
                                    plantsAdapter.submitList(data)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Error desconocido",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            handleLoading(false)
                        }
                        is HomeViewModel.State.Error -> {
                            handleLoading(false)
                            state.message.let {
                                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        HomeViewModel.State.Loading -> {
                            handleLoading(true)
                        }
                    }
                }
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                progress.visibility = View.VISIBLE
                rvPlants.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                rvPlants.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(resultPlants: Plants) {
        Toast.makeText(
            requireContext(),
            "${resultPlants.id}, ${resultPlants.nameplant}, ${resultPlants.status}, ${resultPlants.price}, ${resultPlants.isFavorite}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}