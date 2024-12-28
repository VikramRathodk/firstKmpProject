package org.devvikram.firstpmpproject

import kotlinx.serialization.Serializable

sealed class Destination{

    @Serializable
    data object Home : Destination()

    @Serializable
    data class FavoriteCardDetailScreen(
        val id : String
    ) : Destination()

}