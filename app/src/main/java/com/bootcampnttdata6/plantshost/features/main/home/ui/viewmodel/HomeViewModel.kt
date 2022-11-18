package com.bootcampnttdata6.plantshost.features.main.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.features.main.home.domain.usecase.GetPlantsUseCase
import com.bootcampnttdata6.plantshost.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val plantsUseCase: GetPlantsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchListPlants() {
        viewModelScope.launch(Dispatchers.IO) {
            plantsUseCase().onEach {
                when (it) {
                    is Resource.Error -> _uiState.value = State.Error(it.message)
                    is Resource.Loading -> _uiState.value = State.Loading
                    is Resource.Success -> {
                        _uiState.value = State.Success(it.data)
                    }
                    else -> Resource.Finish
                }
            }.launchIn(viewModelScope)
        }
    }

    sealed class State {
        object Loading : State()
        data class Error(val message: String) : State()
        data class Success(val resultPlants: List<Plants>) : State()
    }
}