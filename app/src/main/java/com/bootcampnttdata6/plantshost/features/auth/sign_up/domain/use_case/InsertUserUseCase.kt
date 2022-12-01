package com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.use_case

import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository.SignUpRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(email: String,name : String, address: String,age: Int) {
        signUpRepository.insertUser(email,name,address,age)
    }
}