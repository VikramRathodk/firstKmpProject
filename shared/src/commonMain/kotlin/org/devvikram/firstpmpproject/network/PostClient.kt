package org.devvikram.firstpmpproject.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.devvikram.Utils.NetworkError
import org.devvikram.Utils.Result
import org.devvikram.firstpmpproject.model.Post

class PostClient (
    private val httpClient: HttpClient
){
    suspend fun getAllPosts() : Result<List<Post> ,NetworkError>{
        val response =  try {
            httpClient.get {
                url {
                    protocol = io.ktor.http.URLProtocol.HTTPS
                    host = "jsonplaceholder.typicode.com"
                    encodedPath = "/posts"

                }
            }

        }catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when(response.status.value){
            in 200..299 -> {
                val posts = response.body<List<Post>>()
                Result.Success(posts)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> {
                Result.Error(NetworkError.UNKNOWN)
            }
        }

    }


}