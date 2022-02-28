package app

import app.component.menu
import app.component.mvp.managedBy
import dev.fritz2.binding.storeOf
import dev.fritz2.components.flexBox
import dev.fritz2.dom.html.render

fun main() {
    val isCollapsed = storeOf(false)

    render() {
        custom("ui5-page") {
            attr("disable-scrolling", true)
            attr("hide-footer", true)
            // header
            custom("ui5-shellbar") {
                this.domNode.slot = "header"
                attr("show-co-pilot", true)
                attr("primary-title", "UI5 Web Components")
                attr("secondary-title", "The Best Run SAP")
                custom("ui5-button", id = "startButton") {
                    attr("icon", "menu")
                    this.domNode.slot = "startButton"
                    clicks.map { !isCollapsed.current } handledBy isCollapsed.update
                }
            }

            //content
            flexBox({
                height { full }
                width { full }
            }) {
                menu(isCollapsed.data)
                section {
                    managedBy(Router.placeManager)
                }
            }

            //footer
            div {
                domNode.slot = "footer"
                +"power by tower"
            }
        }
//        Theme.use(DefaultTheme())

    }
}

