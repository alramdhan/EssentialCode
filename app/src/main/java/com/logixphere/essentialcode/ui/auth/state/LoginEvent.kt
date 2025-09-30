package com.logixphere.essentialcode.ui.auth.state

sealed class LoginEvent {
    data class UserLoginChanged(val value: String) : LoginEvent()
    data class UserPWDChanged(val value: String) : LoginEvent()
    data object Submit : LoginEvent()
    data class Loading(val loading: Boolean) : LoginEvent()
    data class LoginState(
        val userLogin: String = "",
        val userLoginError: Boolean = false,
        val userLoginErrorMsg: String = "",
        val userPwd: String = "",
        val userPwdError: Boolean = false,
        val userPwdErrorMsg: String = "",
        var pwdFieldVisibility: Boolean = false
    )
}