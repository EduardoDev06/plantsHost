package com.bootcampnttdata6.plantshost.features.main.createPlant.presenter
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.features.main.createPlant.domain.usecase.AddImgToFStorageUseCase
import com.bootcampnttdata6.plantshost.features.main.createPlant.domain.usecase.InsertPlantUseCase
import com.bootcampnttdata6.plantshost.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CPViewModel @Inject constructor(
    private val insertPlantUseCase: InsertPlantUseCase,
    private val addImgToFStorageUseCase: AddImgToFStorageUseCase,


): ViewModel(){
    private val _addImgFire = MutableStateFlow<Uri?>(null)
    val addImgFire = _addImgFire.asStateFlow()


     fun addImageToStorage(name:String,imageUri: Uri){
         viewModelScope.launch(Dispatchers.IO) {
             addImgToFStorageUseCase.invoke(name,imageUri).collect{
                 when(it){
                     is Resource.Loading -> ""
                     is Resource.Success -> _addImgFire.value = it.data
                     is Resource.Error -> it.message
                     else -> Resource.Finish
                 }
             }
         }
    }

    fun insertNewPlant(plants: PlantsEntity){
        viewModelScope.launch(Dispatchers.IO){
            insertPlantUseCase(plants)
        }
    }
}