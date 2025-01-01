package org.devvikram.firstpmpproject

import kotlinx.serialization.Serializable


sealed class Destination{

    @Serializable
    data object Home : Destination()
    @Serializable
    data object Task : Destination()

    @Serializable
    data object Post : Destination()


    @Serializable
    data class FavoriteCardDetailScreen(
        val id : String
    ) : Destination()

}