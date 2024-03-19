package com.example.ktor_example.sw

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarWarsCharacter>
)

@Serializable
data class StarWarsCharacter(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: String,
    val edited: String,
    val url: String
)