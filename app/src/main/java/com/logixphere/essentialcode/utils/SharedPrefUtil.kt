package com.logixphere.essentialcode.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.logixphere.essentialcode.data.models.auth.LoginResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

object SecuredSharedPrefs {
    private const val PREFS_FILE_NAME: String = "secureprefs"

    fun getSharedPreferences(context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "${context.packageName}_$PREFS_FILE_NAME",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}

class SharedPrefUtil @Inject constructor() {
    @Inject
    lateinit var prefs: SharedPreferences

    fun saveAfterLogin(obj: LoginResponse) {
        val editor = prefs.edit()
        editor.putString("auth_token", obj.data.token)
        editor.putString("user_fullname", obj.data.fullname)
        editor.putString("user_email", obj.data.useremail)
        editor.putString("api_url", "https://freetestapi.com")
        editor.apply()
    }

    fun loginSesssionDestroy() {
        val editor = prefs.edit()
        editor.remove("auth_token")
        editor.remove("user_fullname")
        editor.remove("user_email")
        editor.remove("api_url")
        editor.apply()
    }

    fun getAuthToken(): String? {
        return prefs.getString("auth_token", null)
    }

    fun getPrefsKey(prefsName: String): String? {
        return prefs.getString(prefsName, "")
    }
}
