package me.larisa.pomidor.application

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.larisa.pomidor.application.models.Card
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img


val StudyComponent = FC<Props> {
    var cardsList by useState(emptyList<Card>())

    useEffectOnce {
        MainScope().launch {
            cardsList = getCardsList()
        }
    }
    div {
        cardsList.forEach { card ->
            div {
                key = card.id.toString()
                div {
                    +card.back
                }
                div {
                    +card.front
                }
                img {
                    src=card.imageUrl
                }
                div {

                }
            }
        }
    }
}