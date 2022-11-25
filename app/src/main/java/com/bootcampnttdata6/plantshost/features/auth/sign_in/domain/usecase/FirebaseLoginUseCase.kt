package com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.usecase
import com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.repository.AuthRepository
import com.bootcampnttdata6.plantshost.features.auth.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator  fun invoke(email: String , password: String): Flow<Resource<Boolean>> = flow{
        emit(Resource.loading)
        val loggedSuccessfully = authRepository.login(email, password)
        if (loggedSuccessfully){
            emit(Resource.Success(true))
            emit(Resource.Finished)
        }else{
            emit(Resource.Error("Login error"))
            emit(Resource.Finished)
        }
    }
}