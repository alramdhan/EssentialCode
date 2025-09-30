package com.logixphere.essentialcode.data.network.api

import com.logixphere.essentialcode.data.models.movies.MovieResponse
import com.logixphere.essentialcode.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET(Constant.ENDPOINT_GET_MOVIES)
    suspend fun getAll(): Response<List<MovieResponse>>
}