package com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.use_case

import com.bootcampnttdata6.plantshost.core.data.remote.UserService
import com.bootcampnttdata6.plantshost.core.data.remote.dto.UserDto
import com.bootcampnttdata6.plantshost.features.auth.sign_in.data.remote.AuthRepositoryImpl
import com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.repository.AuthRepository
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository.SignUpRepository
import com.bootcampnttdata6.plantshost.features.auth.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository)
{

    suspend operator fun invoke(email: String,password : String) {
       signUpRepository.createAuthUser(email,password)
    }
}