package com.logixphere.essentialcode.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logixphere.essentialcode.data.models.BaseResponse
import com.logixphere.essentialcode.data.models.auth.LoginResponse
import com.logixphere.essentialcode.data.repository.LoginRepository
import com.logixphere.essentialcode.data.network.request.LoginRequest
import com.logixphere.essentialcode.utils.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val prefUtil: SharedPrefUtil
): ViewModel() {
    private var _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
    val loginResult: LiveData<BaseResponse<LoginResponse>> = _loginResult
    private val _uiState = MutableStateFlow(LoginEvent.LoginState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.Loading -> _loading.postValue(event.loading)
            is LoginEvent.UserLoginChanged -> _uiState.value = _uiState.value.copy(userLogin = event.value, userLoginError = false, userLoginErrorMsg = "")
            is LoginEvent.UserPWDChanged -> _uiState.value = _uiState.value.copy(userPwd = event.value, userPwdError = false, userPwdErrorMsg = "")
            is LoginEvent.Submit -> {
                if(validateForm()) {
                    signInUser(_uiState.value.userLogin, _uiState.value.userPwd)
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        var result = true

        if(_uiState.value.userLogin.isBlank()) {
            _uiState.value = _uiState.value.copy(userLoginError = true, userLoginErrorMsg = "Username can't be empty")
            result = false
        }
        if(_uiState.value.userPwd.isBlank()) {
            _uiState.value = _uiState.value.copy(userPwdError = true, userPwdErrorMsg = "Password can't be empty")
            result = false
        } else if(_uiState.value.userPwd.length < 6) {
            _uiState.value = _uiState.value.copy(userPwdError = true, userPwdErrorMsg = "Password length must be more than 6")
            result = false
        }

        return result
    }

    fun changeVisiblePassword(visibility: Boolean) {
        _uiState.value = _uiState.value.copy(pwdFieldVisibility = !visibility)
    }

    private fun signInUser(login: String, password: String) = viewModelScope.launch {
        onEvent(LoginEvent.Loading(true))
        delay(1000)

        try {
            val response = repository.login(LoginRequest(login, password))
            if(response.isSuccessful) {
                if(response.code() == 200) {
                    _loginResult.postValue(BaseResponse.Success(response.body()))
                } else {
                    _loginResult.postValue(BaseResponse.Failure(response.code(), response.message()))
                }
            } else {
                _loginResult.postValue(BaseResponse.Failure(responseCode = response.code(), message = response.message()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            _loginResult.postValue(BaseResponse.Failure(responseCode = 500, message = "Terdapat Kesalahan!"))
        }

        onEvent(LoginEvent.Loading(false))
    }

    fun saveUserData(data: LoginResponse) {
        prefUtil.saveAfterLogin(data)
    }
}

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

