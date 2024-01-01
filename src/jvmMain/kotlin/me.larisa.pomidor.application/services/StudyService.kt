package me.larisa.pomidor.application.services

import me.larisa.pomidor.application.db.DatabaseConnection
import me.larisa.pomidor.application.entities.CardsEntity;
import me.larisa.pomidor.application.models.Card
import org.koin.core.annotation.Singleton;
import org.ktorm.dsl.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Singleton
class StudyService() {
    private val db = DatabaseConnection.database

    fun getAll(): List<Card> {
        val today = LocalDateTime.now()
        val twoDaysAgo = today.minus(2, ChronoUnit.DAYS)
        return db.from(CardsEntity).select()
            .where { CardsEntity.score less 0}
            .map {
                Card(it[CardsEntity.id] ?: -1, it[CardsEntity.front] ?: "",
                    it[CardsEntity.back] ?: "", it[CardsEntity.created].toString() ?: "",
                    it[CardsEntity.imageUrl] ?: "", it[CardsEntity.score] ?: -1)
            }
    }

    fun updateFailureById(id: Int, card: Card): Int {
        return db.update(CardsEntity) {
            set(it.score, getNewScore(card, false))
            where {
                it.id eq id
            }
        }
    }

    fun updateSuccessById(id: Int, card: Card): Int {
        return db.update(CardsEntity) {
            set(it.score, getNewScore(card, true))
            where {
                it.id eq id
            }
        }
    }

    private fun getNewScore(card: Card, isSuccess: Boolean): Int {
        val days = Duration.between(LocalDateTime.parse(card.created), LocalDateTime.now()).toDays()
        return when(days) {
            1L -> 4
            else -> 3
        }
    }
}
