
package com.bootcampnttdata6.plantshost.features.main.profile.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import com.bootcampnttdata6.plantshost.features.main.profile.domain.model.User
import kotlinx.coroutines.flow.Flow
import com.bootcampnttdata6.plantshost.util.Result

interface ProfileUserRepository {
    suspend fun updateUserData(user: User)
    suspend fun updateUserPhoto(userImageBitmap: Bitmap?)
    suspend fun getUser(): Flow<Result<User?>>
}
