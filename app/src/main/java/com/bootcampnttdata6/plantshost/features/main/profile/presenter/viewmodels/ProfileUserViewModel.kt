
package com.bootcampnttdata6.plantshost.features.main.profile.presenter.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcampnttdata6.plantshost.features.main.profile.domain.model.User
import com.bootcampnttdata6.plantshost.features.main.profile.domain.usecase.ReadUserUseCase
import com.bootcampnttdata6.plantshost.features.main.profile.domain.usecase.UpdateUserDataUseCase
import com.bootcampnttdata6.plantshost.features.main.profile.domain.usecase.UpdateUserPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bootcampnttdata6.plantshost.util.Result

@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val updateUserPhotoUseCase: UpdateUserPhotoUseCase,
    private val readUserUseCase: ReadUserUseCase
): ViewModel() {

    private val _readUser = MutableStateFlow<Result<User?>>(Result.Loading())
    val readUser = _readUser.asStateFlow()

    init {
        readProfileUser()
    }

    fun updateDataProfileUser(user: User) {
        viewModelScope.launch {
            updateUserDataUseCase(user)
        }
    }

    fun updatePhotoProfileUser(userImageBitmap: Bitmap?) {
        viewModelScope.launch {
            updateUserPhotoUseCase(userImageBitmap)
        }
    }


    fun readProfileUser() {
        viewModelScope.launch(Dispatchers.IO) {
            readUserUseCase().collect() { result ->
                when (result) {
                    is Result.Loading -> {
                        _readUser.value = Result.Loading()
                    }
                    is Result.Success -> {
                        _readUser.value = Result.Success(result.data)
                    }
                    is Result.Failure -> {
                        _readUser.value = Result.Failure(result.exception)
                    }
                    else -> Result.Finish
                }
            }
        }
    }

}
