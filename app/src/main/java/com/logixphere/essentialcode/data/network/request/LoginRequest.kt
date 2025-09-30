package com.logixphere.essentialcode.data.network.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("user_pwd")
    val password: String
)
