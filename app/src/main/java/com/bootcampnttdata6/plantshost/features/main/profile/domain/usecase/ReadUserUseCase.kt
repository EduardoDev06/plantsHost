package com.bootcampnttdata6.plantshost.features.main.profile.domain.usecase

import com.bootcampnttdata6.plantshost.features.main.profile.domain.model.User
import com.bootcampnttdata6.plantshost.features.main.profile.domain.repository.ProfileUserRepository
import javax.inject.Inject
import com.bootcampnttdata6.plantshost.util.Result
import kotlinx.coroutines.flow.Flow

class ReadUserUseCase @Inject constructor(
    private val readRepository: ProfileUserRepository
) {
    suspend operator fun invoke(): Flow<Result<User?>> = readRepository.getUser()
}
