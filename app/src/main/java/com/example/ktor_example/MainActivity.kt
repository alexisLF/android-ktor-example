package com.example.ktor_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ktor_example.sw.StarWarsCharacter
import com.example.ktor_example.sw.StarWarsRepository
import com.example.ktor_example.weather.WeatherData
import com.example.ktor_example.weather.WeatherRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient()

    private val starWarsRepository = StarWarsRepository(httpClient)
    private val weatherRepository = WeatherRepository(httpClient)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Call the function to get Star Wars characters
        getWeatherData()
//        getStarWarsCharacters()

        // Set Jetpack Compose content
        setContent {
            val characters by remember { mutableStateOf(emptyList<StarWarsCharacter>()) }

            CharacterList(characters = characters)
        }
    }

    private fun getStarWarsCharacters() {
        // Use coroutines to make asynchronous network request
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val characters = starWarsRepository.getStarWarsCharacters()
                // Update the Jetpack Compose UI with the received characters
                updateUI(characters)
            } catch (e: Exception) {
                // Handle any exceptions
                e.printStackTrace()
            }
        }
    }

    private fun getWeatherData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val weatherData = weatherRepository.getWeatherData()
                // Update UI with weather data
                updateUIWeather(weatherData)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
            }
        }
    }

    private fun updateUI(characters: List<StarWarsCharacter>) {
        // Update the Jetpack Compose UI on the main (UI) thread
        runOnUiThread {
            setContent {
                CharacterList(characters = characters)
            }
        }
    }
    private fun updateUIWeather(weather: WeatherData) {
        // Update the Jetpack Compose UI on the main (UI) thread
        runOnUiThread {
            setContent {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Wea: ${weather.latitude}")
                        Text(text = "hum: ${weather.longitude}")
                        for(i in 0..2) {
                            Text(text = "T°: ${weather.hourly.time.get(i)}")
                            Text(text = "T°: ${weather.hourly.temperature2m.get(i)}")

                    }
                    }
                }
            }
        }
    }

}

@Composable
fun CharacterList(characters: List<StarWarsCharacter>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        for (character in characters) {
            CharacterItem(character = character)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CharacterItem(character: StarWarsCharacter) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${character.name}")
            Text(text = "Height: ${character.height}")
            Text(text = "Mass: ${character.mass}")
        }
    }
}