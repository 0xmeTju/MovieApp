package com.example.movieapp.ui.welcomescreen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

// Welcome screen composable function with a button to enter the app
// and made to demonstrate the navigation between screens using NavController
@Composable
fun WelcomeScreen(onEnter: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff998FC7)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(                              // Icon for the app
                painter = painterResource(R.drawable.movie_icon),
                modifier = Modifier.size(150.dp),
                contentDescription = null
            )
            Text(                               // Title
                text = "Movie Database App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = onEnter,   // Button to enter the app
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffD4C2FC),
                    contentColor = Color.Black)) {
                Text("Enter")}
        }

        Text(
            text = "Developed by 0xmeTju",
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onEnter = {})
}