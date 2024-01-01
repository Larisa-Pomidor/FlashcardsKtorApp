package me.larisa.pomidor.application.db

import me.larisa.pomidor.application.config.AppConfig
import org.ktorm.database.Database

object DatabaseConnection {
    val database = Database.connect (
        url = AppConfig.applicationConfiguration.propertyOrNull("database.database")?.getString()
            ?: throw IllegalArgumentException("Database URL is not configured."),
        driver = AppConfig.applicationConfiguration.propertyOrNull("database.driver")?.getString()
            ?: throw IllegalArgumentException("Database driver is not configured."),
        user = AppConfig.applicationConfiguration.propertyOrNull("database.user")?.getString()
            ?: throw IllegalArgumentException("Database user is not configured."),
        password = AppConfig.applicationConfiguration.propertyOrNull("database.password")?.getString()
            ?: throw IllegalArgumentException("Database password is not configured.")

    )
}
