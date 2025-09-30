package com.logixphere.essentialcode.data.repository

import com.google.firebase.auth.FirebaseUser
import com.logixphere.essentialcode.utils.Resource

interface BaseAuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<FirebaseUser>
}