package com.example.myapplication.ui

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

object ApiService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }
    suspend fun getApps(): List<Product> {
        val response: ProductResponse = client.get("http://10.0.2.2:8000/apps/").body()
        return response.apps
    }
}
