package com.logixphere.essentialcode.data.models.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("id")
        val id: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("fullname")
        val fullname: String,
        @SerializedName("useremail")
        val useremail: String
    )
}
