package com.bootcampnttdata6.plantshost.features.auth.sign_up.presenter.viewmodels

import androidx.lifecycle.ViewModel
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.use_case.CreateAccountAuthUseCase
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.use_case.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: CreateAccountAuthUseCase,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel(){

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address.asStateFlow()

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age.asStateFlow()

    fun setEmail(text: String) {
        _email.value = text
    }

    fun setPassword(text: String) {
        _password.value=text
    }

    fun setName(text: String) {
        _name.value = text
    }

    fun setAddress(text: String) {
        _address.value=text
    }
    fun setAge(text: String) {
        _age.value=text
    }
    suspend fun createAuthUser(email: String,password: String): String? {
        return signUpUseCase(email,password)
    }
    suspend fun insertUser(email: String,name:String,address:String,age:String, id: String?){
        insertUserUseCase(email, name, address, age.toInt(), id)
    }
}



