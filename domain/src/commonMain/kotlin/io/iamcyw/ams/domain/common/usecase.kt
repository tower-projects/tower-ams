package io.iamcyw.ams.domain.common

sealed class Menu(
        open val id: Long, open val name: String
) {
    /**
     * 菜单
     */
    data class Nav(val subItem: List<NavItem>) : Menu(0, "nav") {
        companion object
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
        companion object
    }

    /**
     * 二级菜单
     */
    data class SubItem(override val id: Long, override val name: String, val icon: String, val url: String) :
            Menu(id, name) {
        companion object
    }
}