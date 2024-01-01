package models

import kotlinx.serialization.Serializable

@Serializable
data class Card (
    val id: Int?,
    val front: String,
    val back: String,
    val created: String,
    val imageUrl: String,
    val score: Int
)