package com.logixphere.essentialcode.ui.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.logixphere.essentialcode.R
import com.logixphere.essentialcode.data.models.BaseResponse
import com.logixphere.essentialcode.data.models.auth.LoginResponse
import com.logixphere.essentialcode.ui.theme.ColorPrimary
import com.logixphere.essentialcode.utils.AppScreen
import com.logixphere.essentialcode.utils.LPTextField
import com.logixphere.essentialcode.utils.ScreenUtil
import com.logixphere.essentialcode.utils.ShowDefaultDialog
import com.logixphere.essentialcode.utils.ShowLoading
import com.logixphere.essentialcode.viewmodels.LoginEvent
import com.logixphere.essentialcode.viewmodels.LoginViewModel

@Composable
fun UserLogin(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val loginResult by viewModel.loginResult.observeAsState()

    when (loginResult) {
        is BaseResponse.Success -> {
            viewModel.saveUserData((loginResult as BaseResponse.Success<LoginResponse>).body!!)
            navController.navigate(AppScreen.HOME.route)
        }
        is BaseResponse.Failure -> {
            val show = true
            ShowDefaultDialog(
                title = "Perhatian",
                text = (loginResult as BaseResponse.Failure).message,
                openDialog = show
            )
        }
        else -> {}
    }
    LoginScreen(viewModel)
}

@Composable
private fun LoginScreen(viewModel: LoginViewModel) {
    val loginState by viewModel.uiState.collectAsState()
    val showLoading by viewModel.loading.observeAsState()

    if(showLoading!!) ShowLoading({})

    Scaffold(
        modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent
    ) { innerPadding ->
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_login),
                contentDescription = "background_login",
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                        .padding(horizontal = 30.dp),
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            content = {
                                Column(modifier = Modifier.width(ScreenUtil.getScreenWidth(.5f))) {
                                    Text(
                                        "Login",
                                        fontSize = TextUnit(
                                            value = 32f,
                                            type = TextUnitType.Sp
                                        ),
                                        fontFamily = FontFamily(
                                            Font(R.font.nicomoji_regular, FontWeight.Normal)
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("Signin by doing it self and lorem ipsum")
                                }
                                Spacer(modifier = Modifier.width(30.dp))
                                Image(
                                    modifier = Modifier.width(80.dp), painter = painterResource(
                                        R.mipmap.ic_launcher_foreground
                                    ), contentDescription = "Logo App"
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LPTextField(
                            value = loginState.userLogin,
                            onValueChange = { viewModel.onEvent(LoginEvent.UserLoginChanged(it)) },
                            label = "Username",
                            isError = loginState.userLoginError,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email
                            )
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        LPTextField(
                            value = loginState.userPwd,
                            onValueChange = { viewModel.onEvent(LoginEvent.UserPWDChanged(it)) },
                            label = "Password",
                            isError = loginState.userPwdError,
                            visualTransformation = if (loginState.pwdFieldVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (loginState.pwdFieldVisibility)
                                    Icons.Rounded.Visibility
                                else
                                    Icons.Filled.VisibilityOff

                                IconButton({ viewModel.changeVisiblePassword(loginState.pwdFieldVisibility) }) {
                                    Icon(image, "Visibility")
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password
                            )
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        OutlinedButton(
                            onClick = {
                                Log.d("LoginActivity", "ini click me")
                                viewModel.onEvent(LoginEvent.Submit)
                            },
                            colors = ButtonColors(
                                ColorPrimary,
                                contentColor = Color.White,
                                disabledContentColor = Color.Black,
                                disabledContainerColor = Color.Black
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clickable(onClick = {})
                        ) {
                            Text("Login", letterSpacing = 1.2.sp, fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                )
                Row(
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .padding(bottom = 64.dp),
                    horizontalArrangement = Arrangement.Center,
                    content = {
                        IconButton(
                            {},
                            modifier = Modifier
                                .shadow(8.dp)
                                .background(color = ColorPrimary, shape = RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            content = {
                                Icon(
                                    Icons.Filled.Fingerprint,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 18.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    content = {
                        Text("Belum punya akun?", color = Color.White, fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        ClickableText(
                            onClick = {},
                            modifier = Modifier
                                .padding(0.dp)
                                .align(Alignment.CenterVertically),
                            style = TextStyle(
                                color = Color.Yellow,
                                fontWeight = FontWeight.SemiBold
                            ),
                            text = AnnotatedString(text = "Daftar di sini")
                        )
                    }
                )
            }
        }
    }
}