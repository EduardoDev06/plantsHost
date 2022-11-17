package com.bootcampnttdata6.plantshost.features.main.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.model.PlantModel
import com.bootcampnttdata6.plantshost.features.main.favorite.domain.usecase.GetPlants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getplants: GetPlants
): ViewModel() {

    private var _listPlants: MutableLiveData<MutableList<PlantModel>> = MutableLiveData()
    var listPlants: LiveData<MutableList<PlantModel>> = _listPlants

    init {
        getPlants()
    }

    fun getPlants(){
        viewModelScope.launch {
            getplants().onEach { plant ->
                _listPlants.value = plant.toMutableList()
            }
        }
    }

}