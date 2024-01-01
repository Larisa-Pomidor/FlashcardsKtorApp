package me.larisa.pomidor.application.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.larisa.pomidor.application.models.Card
import me.larisa.pomidor.application.services.CardsService
import me.larisa.pomidor.application.services.StudyService
import org.koin.ktor.ext.get as koinGet

fun Application.studyRoutes() {
    val studyService: StudyService = koinGet()
    val cardsService: CardsService = koinGet()

    routing {
        get("/api/v1/study/cards") {
            val cards: List<Card> = studyService.getAll()
            call.respond(cards)
        }
        put("/api/v1/study/failure/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1

            val rowsUpdated =
                studyService.updateFailureById(
                    id, cardsService.getCardById(id) ?: throw IllegalStateException("Card with id $id is not found")
                )

            if (rowsUpdated == 1) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        put("/api/v1/study/success/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1

            val rowsUpdated =
                studyService.updateSuccessById(
                    id, cardsService.getCardById(id) ?: throw IllegalStateException("Card with id $id is not found")
                )

            if (rowsUpdated == 1) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}