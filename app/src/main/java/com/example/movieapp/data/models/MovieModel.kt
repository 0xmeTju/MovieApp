package com.example.movieapp.data.models

import com.google.gson.annotations.SerializedName

// Model class to store data coming from api
// SerializedName is used to map the data to the right variables
data class MovieModel (
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: Int,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Error") val error: String?
)