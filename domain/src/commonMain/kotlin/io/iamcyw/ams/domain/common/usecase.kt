package io.iamcyw.ams.domain.common

import dev.fritz2.lenses.Lens
import dev.fritz2.lenses.buildLens

sealed class Menu(
    open val id: Long, open val name: String
) {
    /**
     * 菜单
     */
    data class Nav(val subItem: List<NavItem>) : Menu(0, "nav") {
        companion object {
            fun subItem(): Lens<Nav, List<NavItem>> =
                buildLens("subItem", { it.subItem }, { p, t -> p.copy(subItem = t) })
        }
    }

    /**
     * 一级菜单
     */
    data class NavItem(
        override val id: Long,
        override val name: String,
        val icon: String,
        val url: String? = null,
        val subItem: List<SubItem> = listOf()
    ) : Menu(id, name) {
        companion object {
            fun name(): Lens<NavItem, String> = buildLens("name", { it.name }, { p, t -> p.copy(name = t) })
            fun icon(): Lens<NavItem, String> = buildLens("icon", { it.icon }, { p, t -> p.copy(icon = t) })
            fun url(): Lens<NavItem, String?> = buildLens("url", { it.url }, { p, t -> p.copy(url = t) })

            fun subItem(): Lens<NavItem, List<SubItem>> =
                buildLens("subItem", { it.subItem }, { p, t -> p.copy(subItem = t) })
        }
    }

    /**
     * 二级菜单
     */
    data class SubItem(override val id: Long, override val name: String, val icon: String, val url: String) :
        Menu(id, name) {
        companion object {
            fun name(): Lens<SubItem, String> = buildLens("name", { it.name }, { p, t -> p.copy(name = t) })

            fun icon(): Lens<SubItem, String> = buildLens("icon", { it.icon }, { p, t -> p.copy(icon = t) })

            fun url(): Lens<SubItem, String> = buildLens("url", { it.url }, { p, t -> p.copy(url = t) })
        }
    }
}