package com.logixphere.essentialcode.di

import android.content.Context
import android.content.SharedPreferences
import com.logixphere.essentialcode.data.repository.LoginRepository
import com.logixphere.essentialcode.data.repository.MovieRepository
import com.logixphere.essentialcode.data.network.api.MovieService
import com.logixphere.essentialcode.data.network.api.UserService
import com.logixphere.essentialcode.data.network.firebase.FirebaseAuthenticator
import com.logixphere.essentialcode.data.repository.AuthRepository
import com.logixphere.essentialcode.utils.SecuredSharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun getSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return SecuredSharedPrefs.getSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providesLoginRepository(userService: UserService): LoginRepository = LoginRepository(userService)

    @Provides
    @Singleton
    fun providesMovieRepository(movieService: MovieService): MovieRepository = MovieRepository(movieService)

    @Provides
    @Singleton
    fun providesAuthRepository(firebaseAuthenticator: FirebaseAuthenticator): AuthRepository = AuthRepository(firebaseAuthenticator)
}