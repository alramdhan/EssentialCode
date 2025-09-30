package com.logixphere.essentialcode.di

import com.google.firebase.auth.FirebaseAuth
import com.logixphere.essentialcode.data.network.firebase.FirebaseAuthenticator
import com.logixphere.essentialcode.utils.Constant
import com.logixphere.essentialcode.utils.SharedPrefUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    @Named("Base")
    fun providesRetrofitClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    @Named("Prefs")
    fun providesRetrofitClientURLPrefs(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        sharedPrefUtil: SharedPrefUtil
    ): Retrofit = Retrofit.Builder()
        .baseUrl(sharedPrefUtil.getPrefsKey("api_url")!!)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    class AuthInterceptor @Inject constructor() : Interceptor {
        @Inject
        lateinit var prefs: SharedPrefUtil

        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer ${prefs.getAuthToken()}")

            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesFirebaseAuthenticator(firebaseAuth: FirebaseAuth) = FirebaseAuthenticator(firebaseAuth)
}

