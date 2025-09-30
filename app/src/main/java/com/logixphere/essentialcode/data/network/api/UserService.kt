package com.logixphere.essentialcode.data.network.api

import com.logixphere.essentialcode.data.models.auth.LoginResponse
import com.logixphere.essentialcode.data.network.request.LoginRequest
import com.logixphere.essentialcode.utils.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST(Constant.ENDPOINT_LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET(Constant.ENDPOINT_LOGOUT)
    suspend fun logout(): Response<Any>
}