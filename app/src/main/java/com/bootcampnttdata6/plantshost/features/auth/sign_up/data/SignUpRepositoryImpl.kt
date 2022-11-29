package com.bootcampnttdata6.plantshost.features.auth.sign_up.data

import android.util.Log
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignUpRepository {
    override suspend fun createAuthUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            Log.i("tag", it.isSuccessful.toString())
        }
    }
}