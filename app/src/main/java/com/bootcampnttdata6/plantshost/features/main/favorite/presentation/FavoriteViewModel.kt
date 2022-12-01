package com.bootcampnttdata6.plantshost.features.main.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.usecase.GetPlantsFavoriteUseCase
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getplants: GetPlantsFavoriteUseCase
): ViewModel() {

    private var _listPlants: MutableLiveData<MutableList<Plants>> = MutableLiveData()
    var listPlants: LiveData<MutableList<Plants>> = _listPlants

    init {
        getPlants()
    }

    fun getPlants(){
        viewModelScope.launch{
            getplants().collect{
                _listPlants.value = it.toMutableList()
            }
        }
    }

}