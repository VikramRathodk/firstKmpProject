package org.devvikram.firstpmpproject.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


fun createHttpclient(
    httpClientEngine: HttpClientEngine
): HttpClient{
    return HttpClient(httpClientEngine){
        install(
            ContentNegotiation
        ){
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

    }
}