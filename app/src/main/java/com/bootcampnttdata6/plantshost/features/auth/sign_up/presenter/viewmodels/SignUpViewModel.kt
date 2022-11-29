package com.bootcampnttdata6.plantshost.features.auth.sign_up.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.core.data.remote.dto.UserDto
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.use_case.CreateAccountUseCase
import com.bootcampnttdata6.plantshost.features.auth.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: CreateAccountUseCase
) : ViewModel(){

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setEmail(text: String) {
        _email.value = text
    }

    fun setPassword(text: String) {
        _password.value=text
    }

    fun createAuthUser(email: String,password: String){
        viewModelScope.launch {
            signUpUseCase(email,password)
        }
    }
}



