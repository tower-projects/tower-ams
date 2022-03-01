package app.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.html.Scope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import org.w3c.dom.HTMLElement

class Page(job: Job, scope: Scope) : Tag<HTMLElement>("ui5-page", job = job, scope = scope) {
    fun disScroll(value: Flow<Boolean>) = attr("disable-scrolling", value)
    fun hideFooter(value: Flow<Boolean>) = attr("hide-footer", value)

    fun disScroll(value: Boolean) = attr("disable-scrolling", value)
    fun hideFooter(value: Boolean) = attr("hide-footer", value)

}

fun RenderContext.page(content: Page.() -> Unit): Page =
        register(Page(job, scope), content)