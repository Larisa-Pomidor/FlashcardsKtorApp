package dto

import kotlinx.serialization.Serializable

@Serializable
data class CardDto (
    val front: String,
    val back: String,
    val imageUrl: String
)