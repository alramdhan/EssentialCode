package com.logixphere.essentialcode.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.logixphere.essentialcode.data.repository.AuthRepository
import com.logixphere.essentialcode.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {
    private val _signInState = MutableStateFlow(LoginEvent.LoginState())
    val uiState = _signInState.asStateFlow()
    private var _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.Loading -> _loading.postValue(true)
            is LoginEvent.UserLoginChanged -> _signInState.value = _signInState.value.copy(userLogin = event.value, userLoginError = false, userLoginErrorMsg = "")
            is LoginEvent.UserPWDChanged -> _signInState.value = _signInState.value.copy(userPwd = event.value, userPwdError = false, userPwdErrorMsg = "")
            is LoginEvent.Submit -> {
                if(validateForm()) {
                    signInUser()
                }
            }
        }
    }

    private fun signInUser() {
        viewModelScope.launch {
            val email = _signInState.value.userLogin
            val password = _signInState.value.userPwd
            val authenticated = repository.signInWithEmailAndPassword(email, password)
            when(authenticated) {
                is Resource.Success -> {
//                    _signInState.update { authenticated.data }
                    Log.d("Info", "data ${authenticated.data}")
                }
                is Resource.Error -> throw Exception("Auth failed: ${authenticated.message}")
            }
        }
    }

    private fun validateForm(): Boolean {
        var result = true

        if(_signInState.value.userLogin.isBlank()) {
            _signInState.value = _signInState.value.copy(userLoginError = true, userLoginErrorMsg = "Username can't be empty")
            result = false
        }
        if(_signInState.value.userPwd.isBlank()) {
            _signInState.value = _signInState.value.copy(userPwdError = true, userPwdErrorMsg = "Password can't be empty")
            result = false
        } else if(_signInState.value.userPwd.length < 6) {
            _signInState.value = _signInState.value.copy(userPwdError = true, userPwdErrorMsg = "Password length must be more than 6")
            result = false
        }

        return result
    }

    fun changeVisiblePassword(visibility: Boolean) {
        _signInState.value = _signInState.value.copy(pwdFieldVisibility = !visibility)
    }
}