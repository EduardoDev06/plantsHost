package com.bootcampnttdata6.plantshost.features.main.profile.domain.usecase

import com.bootcampnttdata6.plantshost.features.main.profile.domain.model.User
import com.bootcampnttdata6.plantshost.features.main.profile.domain.repository.ProfileUserRepository
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val profileUserRepository: ProfileUserRepository
) {
    suspend operator fun invoke(user: User) =
        profileUserRepository.updateUserData(user)
}
