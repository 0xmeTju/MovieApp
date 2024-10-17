package com.example.movieapp.data.api

import com.example.movieapp.data.models.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

// Api service interface using retrofit to get data from api
interface ApiService{
    @GET("/")
    suspend fun getMovie(
        @Query("apikey") apiKey: String,    // Free api key from omdbapi.com
        @Query("t") title: String           // Movie title to search info for
    ) : MovieModel                          // Model class to store data
}