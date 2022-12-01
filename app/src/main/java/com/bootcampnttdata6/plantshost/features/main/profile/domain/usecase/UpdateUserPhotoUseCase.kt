package com.bootcampnttdata6.plantshost.features.main.profile.domain.usecase

import android.graphics.Bitmap
import com.bootcampnttdata6.plantshost.features.main.profile.domain.repository.ProfileUserRepository
import javax.inject.Inject

class UpdateUserPhotoUseCase @Inject constructor(
    private val profileUserRepository: ProfileUserRepository
) {
    suspend operator fun invoke(userImageBitmap: Bitmap?) =
        profileUserRepository.updateUserPhoto(userImageBitmap)
}