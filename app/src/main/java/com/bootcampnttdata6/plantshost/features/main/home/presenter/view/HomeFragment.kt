package com.bootcampnttdata6.plantshost.features.main.home.presenter.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.FragmentHomeBinding
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.features.main.home.presenter.adapter.PlantsAdapter
import com.bootcampnttdata6.plantshost.features.main.home.presenter.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val plantsAdapter = PlantsAdapter(this::onClick)
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
        listenerBtn()
        hideScrollFloatingBtn()
        search()
    }

    private fun search() {
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = binding.searchView
        searchView.queryHint = Html.fromHtml("<font color = #959595>" + "Buscar..." + "</font>");
        val searchableInfo = searchManager.getSearchableInfo(requireActivity().componentName)
        searchView.setSearchableInfo(searchableInfo)
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                plantsAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun hideScrollFloatingBtn() {
        binding.rvPlants.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !binding.btnSave.isShown) {
                    binding.btnSave.show()
                } else if (dy > 0 && binding.btnSave.isShown) {
                    binding.btnSave.hide()
                }
            }
        })
    }

    private fun listenerBtn() {
        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_createPlant)
        }
    }

    private fun initRecycler() {
        binding.rvPlants.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = plantsAdapter
        }
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { state ->
                    when (state) {
                        is HomeViewModel.State.Loading -> {
                            handleLoading(true)
                        }
                        is HomeViewModel.State.Success -> {
                            state.resultPlants.let { data ->
                                if (data.isNotEmpty()) {
                                    plantsAdapter.setData(data.toMutableList())
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "No encontramos registros",
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
                    }
                }
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                viewLoading.visibility = View.VISIBLE
                rvPlants.visibility = View.GONE
                btnSave.visibility = View.GONE
                cardSearch.visibility = View.GONE
            } else {
                viewLoading.visibility = View.GONE
                rvPlants.visibility = View.VISIBLE
                btnSave.visibility = View.VISIBLE
                cardSearch.visibility = View.VISIBLE
            }
        }
    }

    private fun onClick(itemPlant: Plants) {
        val action = HomeFragmentDirections.actionHomeToDetailFragment(itemPlant.id)
        findNavController().navigate(action)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                binding.searchView.setQuery(query, false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handleIntent(requireActivity().intent)
    }

    override fun onDestroyView() {
        _binding = null
        handleIntent(requireActivity().intent.setAction(null))
        super.onDestroyView()
    }
}