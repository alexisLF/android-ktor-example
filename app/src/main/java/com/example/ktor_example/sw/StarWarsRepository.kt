package com.example.ktor_example.sw

import io.ktor.client.HttpClient
import io.ktor.client.call.UnsupportedContentTypeException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class StarWarsRepository (private val httpClient: HttpClient) {

    suspend fun getStarWarsCharacters(): List<StarWarsCharacter> {
        try {
            val response: HttpResponse = httpClient.get("https://swapi.dev/api/people/")
            val responseBody: String = response.body<String>()
            val starWarsResponse = Json.decodeFromString<StarWarsResponse>(responseBody)
            return starWarsResponse.results
        } catch (e: ClientRequestException) {
            // Handle client request exceptions
            e.printStackTrace()
        } catch (e: ServerResponseException) {
            // Handle server response exceptions
            e.printStackTrace()
        } catch (e: RedirectResponseException) {
            // Handle redirect exceptions
            e.printStackTrace()
        } catch (e: UnsupportedContentTypeException) {
            // Handle unsupported content type exceptions
            e.printStackTrace()
        } catch (e: Throwable) {
            // Handle other exceptions
            e.printStackTrace()
        } finally {
            httpClient.close() // Close the HttpClient
        }
        return emptyList()
    }
}
