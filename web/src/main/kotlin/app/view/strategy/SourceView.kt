package app.view.strategy

import app.component.mvp.Presenter
import app.component.mvp.View
import app.component.mvp.ViewContent

class SourceView : View {
    override val content: ViewContent = {
        p { +"🍎" }
    }
}

class SourcePresenter : Presenter<SourceView> {
    override val view = SourceView()
}