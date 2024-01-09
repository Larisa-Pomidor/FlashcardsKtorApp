package me.larisa.pomidor.application

import me.larisa.pomidor.application.dto.CardDto
import js.core.get
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.router.useNavigate
import react.router.useParams

val EditCardComponent = FC<Props>{
    val navigate = useNavigate()
    val cardId = useParams()["cardId"]?.toIntOrNull() ?: -1

    val (front, setFront) = useState("")
    val (back, setBack) = useState("")
    val (imageUrl, setImageUrl) = useState("")

    useEffectOnce {
        MainScope().launch {
            val cardApi = getCardById(cardId)
            setBack(cardApi.back)
            setFront(cardApi.front)
            setImageUrl(cardApi.imageUrl)
        }
    }
    div {
        ReactHTML.form {
            ReactHTML.label {
                +"Front:"
                ReactHTML.input {
                    type = InputType.text
                    value = front
                    onChange = { event ->
                        setFront(event.target.value)
                    }
                }
            }
            ReactHTML.label {
                +"Back:"
                ReactHTML.input {
                    type = InputType.text
                    value = back
                    onChange = { event ->
                        setBack(event.target.value)
                    }
                }
            }
            ReactHTML.label {
                +"Image URL:"
                ReactHTML.input {
                    type = InputType.text
                    value = imageUrl
                    onChange = { event ->
                        setImageUrl(event.target.value)
                    }
                }
            }
            ReactHTML.button {
                type = ButtonType.button
                onClick = { event ->
                    event.preventDefault()
                    MainScope().launch {
                        val card = CardDto(back, front, imageUrl)
                        updateCard(cardId, card)
                        navigate("/cards")
                    }
                }
                +"Update Card"
            }
        }
    }
}