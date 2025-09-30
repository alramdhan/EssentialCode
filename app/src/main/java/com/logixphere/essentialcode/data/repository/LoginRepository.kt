package com.logixphere.essentialcode.data.repository

import android.util.Log
import com.logixphere.essentialcode.data.models.auth.LoginResponse
import com.logixphere.essentialcode.data.network.api.UserService
import com.logixphere.essentialcode.data.network.request.LoginRequest
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userService: UserService) {
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        Log.d("LoginRepository", "masuk sini harusnya")
        return userService.login(request)
    }

    suspend fun logout(): Response<Any> {
        return userService.logout()
    }
}