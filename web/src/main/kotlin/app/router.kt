package app

import app.component.mvp.PlaceManager
import app.component.mvp.PlaceRequest
import app.component.mvp.Presenter
import app.view.strategy.SourcePresenter

class Router {
    companion object {
        init {
            Presenter.register("/strategy/source", ::SourcePresenter)
        }

        internal val placeManager: PlaceManager = PlaceManager(PlaceRequest("/strategy/source")) {
            p { +"💣" }
        }

    }
}
