import js.core.get
import react.FC
import react.Key
import react.Props
import react.createElement
import react.router.Route
import react.router.Routes
import react.router.useParams

val Header = FC<Props> {
    Routes {
        Route {
            path = "/study"
            element = createElement(StudyComponent)
        }
        Route {
            path = "/cards"
            element = createElement(CardsComponent)
        }
        Route {
            path = "/edit"
            element = createElement(EditCardComponent)
        }
        Route {
            path = "/add"
            element = createElement(AddCardComponent)
        }
        Route {
            path = "/edit/:cardId"
            element = createElement(EditCardComponent)
        }
   }
}