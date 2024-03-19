package com.example.ktor_example.weather

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WeatherRepository(private val httpClient: HttpClient) {
    suspend fun getWeatherData(): WeatherData {
        val url =
            "https://api.open-meteo.com/v1/forecast?latitude=49.1859&longitude=-0.3591&hourly=temperature_2m"
        val response: HttpResponse = httpClient.get(url)
        val responseBody: String = response.body<String>()
        return Json.decodeFromString<WeatherData>(responseBody)
    }
}
