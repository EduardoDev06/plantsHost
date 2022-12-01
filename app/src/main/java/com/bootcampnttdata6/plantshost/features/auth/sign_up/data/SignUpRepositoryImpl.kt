package com.bootcampnttdata6.plantshost.features.auth.sign_up.data

import android.util.Log
import com.bootcampnttdata6.plantshost.di.FirebaseModule.provideFirestore
import com.bootcampnttdata6.plantshost.features.auth.sign_up.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignUpRepository {
    override suspend fun createAuthUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        }
    }
    override suspend fun insertUser(email: String, name: String, address: String, age: Int) {
        val id = firebaseAuth.uid

        val newUser = hashMapOf(
            "userEmail" to email,
            "userFullName" to name,
            "userAddress" to address,
            "userAge" to age,
            "userImage" to ""
        )
        id?.let {
            provideFirestore().collection("users").document(it).set(newUser)
                .addOnSuccessListener {
                    Log.d(
                        "INSERT_USER",
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e -> Log.w("INSERT_USER", "Error writing document", e); }
        }
    }
}