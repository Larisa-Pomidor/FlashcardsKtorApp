package me.larisa.pomidor.application.plugins

import io.ktor.server.application.*
import me.larisa.pomidor.application.routing.cardsRoutes
import me.larisa.pomidor.application.routing.studyRoutes

fun Application.configureRouting() {
    cardsRoutes()
    studyRoutes()
}