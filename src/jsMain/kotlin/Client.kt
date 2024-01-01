import react.FC
import react.Props
import web.dom.document
import react.create
import react.createElement
import react.dom.client.createRoot
import react.dom.html.ReactHTML.div
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    val main = FC<Props> {
        BrowserRouter {
            Header()
        }
    }
    createRoot(container).render(main.create())
}
