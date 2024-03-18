package me.larisa.pomidor.application.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import me.larisa.pomidor.application.dto.CardDto
import me.larisa.pomidor.application.models.Card
import me.larisa.pomidor.application.services.CardsService
import org.koin.ktor.ext.get as koinGet

fun Application.cardsRoutes() {
    val cardsService: CardsService = koinGet()

    routing {
        get("/api/v1/cards") {
            val cards: List<Card> = cardsService.getAll()
            call.respond(cards)
        }
        get("/api/v1/cards/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1

            val card = cardsService.getCardById(id)

            if (card != null) {
                call.respond(card)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post("/api/v1/cards") {
            val cardDto = call.receive<CardDto>()
            val rowsAdded = cardsService.addCard(cardDto)
            if (rowsAdded == 1) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        delete("/api/v1/cards/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1

            val rowsDeleted = cardsService.deleteCardById(id)

            if (rowsDeleted == 1) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        put("/api/v1/cards/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val card = call.receive<Card>()

            val rowsUpdated = cardsService.updateById(id, card)

            if (rowsUpdated == 1) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}