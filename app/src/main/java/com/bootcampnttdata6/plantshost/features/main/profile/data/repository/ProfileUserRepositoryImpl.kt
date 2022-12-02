package com.bootcampnttdata6.plantshost.features.main.profile.data.repository


import android.graphics.Bitmap
import android.net.Uri
import com.bootcampnttdata6.plantshost.features.main.profile.domain.model.User

import com.bootcampnttdata6.plantshost.features.main.profile.domain.repository.ProfileUserRepository
import com.bootcampnttdata6.plantshost.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ProfileUserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) : ProfileUserRepository {

    override suspend fun updateUserPhoto(userImageBitmap: Bitmap?) {
        val userAuth = firebaseAuth.currentUser
        val id = userAuth?.uid
        val imageRef = firebaseStorage.reference.child("users/${userAuth?.uid}")
        val baos = ByteArrayOutputStream()
        userImageBitmap?.compress(Bitmap.CompressFormat.PNG, 20, baos)
        id?.let {
            firebaseFirestore.collection("users").document(it).update(
                mapOf("userImage" to imageRef.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()
                ))
        }
    }

    override suspend fun updateUserData(user: User) {
        val userAuth = firebaseAuth.currentUser
        val id = userAuth?.uid
        id?.let { id ->
            if (user.userPassword.isNotEmpty()) {
                userAuth.updatePassword(user.userPassword)
            }
            firebaseFirestore.collection("users").document(id)
                .update(
                    mapOf(
                        "userFullName" to user.userFullName,
                        "userAddress" to user.userAddress,
                        "userAge" to user.userAge
                    )
                )
        }
    }

    override suspend fun getUser(): Flow<Result<User?>> = flow {
        emit(Result.Loading())
        try {
            val uid = firebaseAuth.currentUser?.uid
            val userFirebase = uid?.let {
                firebaseFirestore.collection("users").document(it)
            }
            val user = userFirebase?.get()?.await()?.toObject(User::class.java)
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}