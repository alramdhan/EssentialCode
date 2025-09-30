package com.logixphere.essentialcode.di

import com.google.firebase.auth.FirebaseAuth
import com.logixphere.essentialcode.data.network.firebase.FirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

//    @Provides
//    @Singleton
//    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}
