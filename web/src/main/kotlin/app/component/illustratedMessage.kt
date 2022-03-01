package app.component

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.RenderContext
import dev.fritz2.dom.html.Scope
import kotlinx.coroutines.Job
import org.w3c.dom.HTMLElement

class IllustratedMessage(job: Job, scope: Scope) : Tag<HTMLElement>("ui5-illustrated-message", job = job, scope = scope) {
    fun name(value: Illustrated) = attr("name", value.name)
}

fun RenderContext.illustratedMessage(content: IllustratedMessage.() -> Unit): IllustratedMessage =
        register(IllustratedMessage(job, scope), content)

enum class Illustrated {
    PageNotFound

}