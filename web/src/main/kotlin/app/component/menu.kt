package app.component

import app.Router
import app.component.mvp.placeRequest
import dev.fritz2.binding.storeOf
import dev.fritz2.binding.sub
import dev.fritz2.dom.html.RenderContext
import io.iamcyw.ams.domain.common.Menu
import kotlinx.coroutines.flow.Flow


fun RenderContext.menu(isCollapsed: Flow<Boolean>) {
    val nav: Menu.Nav = Menu.Nav(
        listOf(
            Menu.NavItem(
                1L,
                "strategy",
                "home",
                subItem = listOf(Menu.SubItem(2L, "source", "home", "/strategy/source"))
            )
        )
    )
    val subNavStore = storeOf(nav.subItem)

    sideNavigation {

        collapsed(isCollapsed)
        subNavStore.data.renderEach(Menu.NavItem::id, into = this) { navItem ->

            sideNavigationItem {
                val navItemStore = subNavStore.sub(navItem, Menu.NavItem::id)
                text(navItemStore.sub(Menu.NavItem.name()).data)
                icon(navItemStore.sub(Menu.NavItem.icon()).data)

                navItem.url?.let { url ->
                    if (navItem.subItem.isEmpty()) {
                        domNode.addEventListener("click", {
                            Router.placeManager.router.navTo(placeRequest(url))
                        })
                    }
                }

                val subItemsStore = navItemStore.sub(Menu.NavItem.subItem())
                subItemsStore.data.renderEach(Menu.SubItem::id, into = this) { subItem ->
                    sideNavigationSubItem {
                        val subItemStore = subItemsStore.sub(subItem, Menu.SubItem::id)
                        text(subItemStore.sub(Menu.SubItem.name()).data)
                        icon(subItemStore.sub(Menu.SubItem.icon()).data)
                        domNode.addEventListener("click", {
                            Router.placeManager.router.navTo(placeRequest(subItem.url))
                        })
                    }

                }
            }

        }
    }

}