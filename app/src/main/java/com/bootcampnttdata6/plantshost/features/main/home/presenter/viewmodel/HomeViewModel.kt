package com.bootcampnttdata6.plantshost.features.main.home.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import com.bootcampnttdata6.plantshost.features.main.home.domain.usecase.GetPlantsUseCase
import com.bootcampnttdata6.plantshost.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val plantsUseCase: GetPlantsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchListPlants() {
        viewModelScope.launch(Dispatchers.IO) {
            plantsUseCase().collect{
                when (it) {
                    is Resource.Loading -> _uiState.value = State.Loading
                    is Resource.Success -> _uiState.value = State.Success(it.data)
                    is Resource.Error -> _uiState.value = State.Error(it.message)
                    else -> Resource.Finish
                }
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Error(val message: String) : State()
        data class Success(val resultPlants: List<Plants>) : State()
    }
}