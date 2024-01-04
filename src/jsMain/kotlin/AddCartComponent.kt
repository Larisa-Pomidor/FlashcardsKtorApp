import dto.CardDto
import io.ktor.client.fetch.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.useState

val AddCardComponent = FC<Props> {
    val (front, setFront) = useState("")
    val (back, setBack) = useState("")
    val (imageUrl, setImageUrl) = useState("")

    val handleSubmit: (Event) -> Unit = { event ->
        event.preventDefault()

        val newCard = CardDto(
            front = front,
            back = back,
            imageUrl = imageUrl
        )

        MainScope().launch {
            addCard(newCard)
        }

        setFront("")
        setBack("")
        setImageUrl("")
    }

    div {
        form {
            label {
                +"Front:"
                input {
                    type = InputType.text
                    value = front
                    onChange = { event ->
                        setFront(event.target.value)
                    }
                }
            }
            label {
                +"Back:"
                input {
                    type = InputType.text
                    value = back
                    onChange = { event ->
                        setBack(event.target.value)
                    }
                }
            }
            label {
                +"Image URL:"
                input {
                    type = InputType.text
                    value = imageUrl
                    onChange = { event ->
                        setImageUrl(event.target.value)
                    }
                }
            }
            button {
                type = ButtonType.button
                onClick = { event ->
                    event.preventDefault()

                    val newCard = CardDto(
                        front = front,
                        back = back,
                        imageUrl = imageUrl
                    )

                    MainScope().launch {
                        addCard(newCard)
                    }

                    setFront("")
                    setBack("")
                    setImageUrl("")
                }
                +"Add Card"
            }
        }
    }
}