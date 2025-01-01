package org.devvikram.firstpmpproject

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import org.devvikram.firstpmpproject.network.createHttpclient

class AndroidKtorClient {
    companion object {
         val ktorClient = createHttpclient(
            OkHttp.create()
        )
    }
}
