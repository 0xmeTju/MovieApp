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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.MovieState
import java.net.UnknownHostException

// Search screen composable function
@Composable
fun SearchScreen(modifier: Modifier = Modifier,viewModel: SearchScreenViewModel = hiltViewModel()) {
    var searchMovie by remember { mutableStateOf("") }          // Movie title to search for
    val movieState by viewModel.movieState.collectAsState()     // Observing the state of the movie data

    // Column to hold the search bar and search button
    Column (modifier = modifier.fillMaxSize().background(Color(0xff998FC7))) {
        TextField(
            value = searchMovie,
            onValueChange = { searchMovie = it },
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
                .background(Color(0xff998FC7), shape = RoundedCornerShape(10.dp)),
            placeholder = { Text("Enter movie title", color = Color(0xFF9CA3AF)) },
            singleLine = true
        )
        OutlinedButton(
            onClick = { viewModel.getMovie(searchMovie) },      // Call getMovie function from viewModel
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .height(48.dp)
                .shadow(5.dp, shape = RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffD4C2FC),
                contentColor = Color.Black)
        ) {
            Text("Search")
        }
    }

    // Box to display the output of search
    Box (contentAlignment = Alignment.Center) {
        // Check the state of the movie data
        when (movieState) {
            is MovieState.Loading -> CircularProgressIndicator()                // Show loading indicator if data is being fetched
            is MovieState.Success -> {                                          // Show movie details if data is fetched correctly
                val movie = (movieState as MovieState.Success).movie            // Get movie data from state by recasting it

                // If error is not null, show error message coming from api, else show movie details
                if (movie.error != null) {
                    Text("Error: ${movie.error}", fontSize = 24.sp, fontWeight = FontWeight.Medium)
                } else {
                    MovieDetails(movie)
                }
            }
            // Show error message if data fetching failed
            is MovieState.Error -> {
                val error = (movieState as MovieState.Error).exception          // Get exception from state by recasting it
                // If exception is UnknownHostException, show no internet connection message, else show error message
                if (error is UnknownHostException) {
                    Text("No internet connection", fontSize = 24.sp, fontWeight = FontWeight.Medium)
                } else {
                    Text("${error.message}", fontSize = 24.sp, fontWeight = FontWeight.Medium)
                }
            }
            is MovieState.Idle -> {}                                            // Do nothing if data is not being fetched
        }
    }
}

// Movie details composable function
@Composable
fun MovieDetails(movie: MovieModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff867db0))
            .padding(16.dp)
    ) {
        Text("Title: ${movie.title}", modifier = Modifier.padding(top = 4.dp), fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("Year: ${movie.year}",modifier = Modifier.padding(top = 4.dp), fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Text("Genre: ${movie.genre}", modifier = Modifier.padding(top = 4.dp), fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Text("Released: ${movie.released}",  modifier = Modifier.padding(top = 4.dp), fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Text("Director: ${movie.director}", modifier = Modifier.padding(top = 4.dp), fontSize = 24.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}