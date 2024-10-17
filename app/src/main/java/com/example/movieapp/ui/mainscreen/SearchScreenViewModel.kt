/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.movieapp.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.movieapp.data.MainScreenRepository
import com.example.movieapp.data.models.MovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// Search screen view model
@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) : ViewModel() {

    // State flow to hold the state of the movie data, this value is private hence the underscore
    private val _movieState = MutableStateFlow<MovieState>(MovieState.Idle)
    val movieState: StateFlow<MovieState> = _movieState.asStateFlow()               // Exposing the state to the search screen

    // Get movie data from repository and update the state
    fun getMovie(title: String) {
        viewModelScope.launch {
            _movieState.value = MovieState.Loading                                  // Set the state to loading before fetching data
            try {
                val movie = mainScreenRepository.getMovie(title)                    // Get movie data from repository using the getMovie function
                _movieState.value = MovieState.Success(movie)
            }
            catch (e: Exception) {                                                  // Catch any exceptions and update the state accordingly
                _movieState.value = MovieState.Error(e)
            }
        }
    }
}
