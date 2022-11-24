package com.bootcampnttdata6.plantshost.features.auth.sign_in.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.usecase.FirebaseLoginUseCase
import com.bootcampnttdata6.plantshost.features.auth.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import  javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase : FirebaseLoginUseCase
):ViewModel(){

    private val _loginState:MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val loginState:LiveData<Resource<Boolean>>
        get() =_loginState

    fun login(email: String , password:String)
    {
        viewModelScope.launch {
            loginUseCase(email,password).onEach{state->
                _loginState.value=state
            }.launchIn(viewModelScope)
        }

    }
}