package me.larisa.pomidor.application.plugins

import io.ktor.server.application.*
import me.larisa.pomidor.application.db.DatabaseConnection
import me.larisa.pomidor.application.services.CardsService
import me.larisa.pomidor.application.services.StudyService
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    val appModule = module {
        single { CardsService() }
        single { StudyService() }
    }

    install(Koin) {
        slf4jLogger()
        modules(
            appModule
        )
    }
}