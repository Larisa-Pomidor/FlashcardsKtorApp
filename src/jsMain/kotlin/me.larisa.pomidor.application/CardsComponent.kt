package me.larisa.pomidor.application

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.larisa.pomidor.application.models.Card
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.useEffectOnce
import react.useState

val CardsComponent = FC<Props> {
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
                    onClick = {
                        MainScope().launch {
                            deleteCard(card)
                        }
                    }
                }
                div {
                    a {
                        href = "/edit/${card.id}"
                        + "Edit"
                    }
                }
            }
        }
    }
}