package com.logixphere.essentialcode.data.models.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("genre")
    val genre: List<String>,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("director")
    val director: String,
    @SerializedName("actors")
    val actors: List<String>,
    @SerializedName("plot")
    val plot: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("trailer")
    val trailer: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("awards")
    val awards: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("boxOffice")
    val boxOffice: String,
    @SerializedName("production")
    val production: String,
    @SerializedName("website")
    val website: String
)