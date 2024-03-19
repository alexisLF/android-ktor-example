package com.example.ktor_example.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationtimeMs: Double,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val hourly: HourlyData,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits
)

@Serializable
data class HourlyData(
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature2m: List<Double>
)

@Serializable
data class HourlyUnits(
    val time: String,
    @SerialName("temperature_2m")
    val temperature2m: String
)

