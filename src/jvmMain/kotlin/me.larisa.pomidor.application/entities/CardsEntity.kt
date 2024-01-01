package me.larisa.pomidor.application.entities

import org.ktorm.schema.*

object CardsEntity: Table<Nothing>("cards") {
    val id = int("id").primaryKey()
    val front = varchar("front")
    val back = varchar("back")
    val created = datetime("created")
    val imageUrl = varchar("image_url")
    val score = int("score")
}