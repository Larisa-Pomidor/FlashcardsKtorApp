package me.larisa.pomidor.application.services;

import me.larisa.pomidor.application.db.DatabaseConnection
import me.larisa.pomidor.application.dto.CardDto
import me.larisa.pomidor.application.entities.CardsEntity
import me.larisa.pomidor.application.models.Card
import org.koin.core.annotation.Singleton;
import org.ktorm.dsl.*
import java.time.LocalDateTime

@Singleton
class CardsService() {
    private val db = DatabaseConnection.database

    fun getAll(): List<Card> {
        return db.from(CardsEntity).select()
            .map {
                Card(it[CardsEntity.id] ?: -1, it[CardsEntity.front] ?: "",
                    it[CardsEntity.back] ?: "", it[CardsEntity.created].toString() ?: "",
                    it[CardsEntity.imageUrl] ?: "", it[CardsEntity.score] ?: -1)
            }
    }

    fun getCardById (id: Int) : Card? {
        return db.from(CardsEntity)
            .select()
            .where(CardsEntity.id eq id)
            .map {
                Card(it[CardsEntity.id] ?: -1, it[CardsEntity.front] ?: "",
                    it[CardsEntity.back] ?: "", it[CardsEntity.created].toString(),
                    it[CardsEntity.imageUrl] ?: "", it[CardsEntity.score] ?: -1)
            }.firstOrNull()
    }

    fun deleteCardById(id: Int): Int {
        return db.delete(CardsEntity) {
            CardsEntity.id eq id
        }
    }
    fun addCard(card: CardDto): Int {
        return db.insert(CardsEntity) {
            set(it.created, LocalDateTime.now())
            set(it.back, card.back)
            set(it.front, card.front)
            set(it.imageUrl, card.imageUrl)
            set(it.score, 0)
        }
    }

    fun updateById(id: Int, card: Card): Int {
        return db.update(CardsEntity) {
            set(it.back, card.back)
            set(it.front, card.front)
            set(it.imageUrl, card.imageUrl)
            //set(it.created, card.created)
            set(it.score, card.score)
            where {
                it.id eq id
            }
        }
    }
}
