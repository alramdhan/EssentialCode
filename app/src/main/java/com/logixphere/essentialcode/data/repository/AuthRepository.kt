package com.logixphere.essentialcode.data.repository

import com.google.firebase.auth.FirebaseUser
import com.logixphere.essentialcode.data.network.firebase.FirebaseAuthenticator
import com.logixphere.essentialcode.utils.Resource
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuthenticator: FirebaseAuthenticator) : BaseAuthRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val users = firebaseAuthenticator.signInWithEmailPassword(email, password)
            Resource.Success(users!!)
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An unknown error occured.")
        }
    }

}