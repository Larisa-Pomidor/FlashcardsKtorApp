import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import models.Card

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getCardsList(): List<Card> {
    return jsonClient.get("/api/v1/cards").body()
}

suspend fun addCard(card: Card) {
    jsonClient.post("/api/v1/cards") {
        contentType(ContentType.Application.Json)
        setBody(card)
    }
}

suspend fun deleteCard(card: Card) {
    jsonClient.delete("/api/v1/cards/${card.id}")
}

suspend fun getStudyList(): List<Card> {
    return jsonClient.get("/api/v1/study/cards").body()
}