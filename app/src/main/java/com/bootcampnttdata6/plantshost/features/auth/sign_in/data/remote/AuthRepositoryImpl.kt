package com.bootcampnttdata6.plantshost.features.auth.sign_in.data.remote

import com.bootcampnttdata6.plantshost.features.auth.sign_in.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
 private val firebaseAuth: FirebaseAuth
): AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
       return try {
           var isSuccessfull=false
           firebaseAuth.signInWithEmailAndPassword(email,password)
               .addOnSuccessListener { isSuccessfull=true }
               .addOnFailureListener {isSuccessfull=false }
               .await()
           isSuccessfull
       }catch (e:Exception){
           false
       }
    }
}