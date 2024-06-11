package com.castro.helena.myopenweatherapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.castro.helena.myopenweatherapi.navigation.AppNavHost
import com.castro.helena.myopenweatherapi.navigation.NavigationItem
import com.castro.helena.myopenweatherapi.ui.theme.MyOpenWeatherApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyOpenWeatherApiTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Magenta) {
                    AppNavHost(
                        navHostController = rememberNavController(),
                        startDestination = NavigationItem.HomeScreen.route
                    )
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyOpenWeatherApiTheme {
//        Greeting("Android")
//    }
//}