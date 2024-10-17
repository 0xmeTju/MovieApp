package com.example.movieapp.data.models

// Sealed class to handle states of the app and movie data
sealed class MovieState {
    object Loading : MovieState()                               // Loading state where data is being fetched
    object Idle : MovieState()                                  // Idle state where data is not being fetched
    data class Success(val movie: MovieModel) : MovieState()    // Success state where data is fetched and movie model is filled
    data class Error(val exception: Throwable) : MovieState()   // Error state where data fetching failed and exception is filled
}