package app.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.html.Scope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import org.w3c.dom.HTMLElement

class SideNavigation(job: Job, scope: Scope) : Tag<HTMLElement>("ui5-side-navigation", job = job, scope = scope) {
    fun collapsed(value: Flow<Boolean>) = attr("collapsed", value)
}

fun RenderContext.sideNavigation(content: SideNavigation.() -> Unit): SideNavigation =
    register(SideNavigation(job, scope), content)

class SideNavigationItem(job: Job, scope: Scope) :
    Tag<HTMLElement>("ui5-side-navigation-item", job = job, scope = scope) {
    init {
        domNode.slot = "default"
    }

    fun text(value: Flow<String>) = attr("text", value)
    fun icon(value: Flow<String>) = attr("icon", value)
}

fun RenderContext.sideNavigationItem(content: SideNavigationItem.() -> Unit): SideNavigationItem =
    register(SideNavigationItem(job, scope), content)


class SideNavigationSubItem(job: Job, scope: Scope) :
    Tag<HTMLElement>("ui5-side-navigation-sub-item", job = job, scope = scope) {
    init {
        domNode.slot = "default"
    }

    fun text(value: Flow<String>) = attr("text", value)
    fun icon(value: Flow<String>) = attr("icon", value)
}

fun RenderContext.sideNavigationSubItem(content: SideNavigationSubItem.() -> Unit): SideNavigationSubItem =
    register(SideNavigationSubItem(job, scope), content)