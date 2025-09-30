package com.logixphere.essentialcode.di

import com.logixphere.essentialcode.data.network.ServiceBuilder
import com.logixphere.essentialcode.data.network.ServiceBuilderPrefs
import com.logixphere.essentialcode.data.network.api.MovieService
import com.logixphere.essentialcode.data.network.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideUserService(serviceBuilder: ServiceBuilder): UserService = serviceBuilder
        .buildService(UserService::class.java)

    @Provides
    @Singleton
    fun providesMovieService(serviceBuilderPrefs: ServiceBuilderPrefs): MovieService = serviceBuilderPrefs
        .buildService(MovieService::class.java)
}