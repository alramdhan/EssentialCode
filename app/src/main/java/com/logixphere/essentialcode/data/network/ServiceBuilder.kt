package com.logixphere.essentialcode.data.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class ServiceBuilder @Inject constructor(@Named("Base") private val retrofit: Retrofit) {
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}

class ServiceBuilderPrefs @Inject constructor(@Named("Prefs") private val retrofit: Retrofit) {
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}