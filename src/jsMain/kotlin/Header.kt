import react.FC
import react.Props
import react.createElement
import react.router.Route
import react.router.Routes

val Header = FC<Props>{
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
    }
}