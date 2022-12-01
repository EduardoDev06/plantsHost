package com.bootcampnttdata6.plantshost.features.main.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.main.detail.domain.usecase.ChangePlant
import com.bootcampnttdata6.plantshost.features.main.detail.domain.usecase.GetPlant
import com.bootcampnttdata6.plantshost.features.main.home.domain.model.Plants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPlant: GetPlant,
    private val changePlant: ChangePlant
) : ViewModel () {

    private val _showDataPlant : MutableLiveData<Plants> = MutableLiveData()
    val showDataPlant : LiveData<Plants> = _showDataPlant

    fun changeFav(id: Int,isFavorite: Boolean){
        viewModelScope.launch {
            changePlant(id,isFavorite)
        }
    }

    fun getDataPlant(id: Int) {
        viewModelScope.launch {
            getPlant(id).let {
                _showDataPlant.value=it
            }
        }
    }
}