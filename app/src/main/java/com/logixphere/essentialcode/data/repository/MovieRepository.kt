package com.logixphere.essentialcode.data.repository

import com.logixphere.essentialcode.data.models.movies.MovieResponse
import com.logixphere.essentialcode.data.network.api.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {
    suspend fun getAll(): Response<List<MovieResponse>> {
        return movieService.getAll()
    }
}