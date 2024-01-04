package me.larisa.pomidor.application

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.Netty
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import me.larisa.pomidor.application.plugins.configureRouting
import io.ktor.server.plugins.contentnegotiation.*
import me.larisa.pomidor.application.plugins.configureKoin

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/untitled.js") {}
    }
}
fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {

        configureKoin()
        configureRouting()
        routing {
            route("{...}") {
                handle {
                    call.respondHtml(HttpStatusCode.OK, HTML::index)
                }
            }
            static("/static") {
                resources()
            }
        }
        install(ContentNegotiation) {
            json()
        }
    }.start(wait = true)
}