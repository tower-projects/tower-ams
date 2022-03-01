package io.iamcyw.ams.domain.common

import dev.fritz2.lenses.Lens
import dev.fritz2.lenses.buildLens


public fun Menu.NavItem.Companion.id(): Lens<Menu.NavItem, Long> = buildLens(
        "id",
        { it.id },
        { p, v -> p.copy(id = v)}
)

public fun Menu.NavItem.Companion.name(): Lens<Menu.NavItem, String> = buildLens(
        "name",
        { it.name },
        { p, v -> p.copy(name = v)}
)

public fun Menu.NavItem.Companion.icon(): Lens<Menu.NavItem, String> = buildLens(
        "icon",
        { it.icon },
        { p, v -> p.copy(icon = v)}
)

public fun Menu.NavItem.Companion.url(): Lens<Menu.NavItem, String?> = buildLens(
        "url",
        { it.url },
        { p, v -> p.copy(url = v)}
)

public fun Menu.NavItem.Companion.subItem(): Lens<Menu.NavItem, List<Menu.SubItem>> = buildLens(
        "subItem",
        { it.subItem },
        { p, v -> p.copy(subItem = v)}
)

public fun Menu.Nav.Companion.subItem(): Lens<Menu.Nav, List<Menu.NavItem>> = buildLens(
        "subItem",
        { it.subItem },
        { p, v -> p.copy(subItem = v)}
)

public fun Menu.SubItem.Companion.id(): Lens<Menu.SubItem, Long> = buildLens(
        "id",
        { it.id },
        { p, v -> p.copy(id = v)}
)

public fun Menu.SubItem.Companion.name(): Lens<Menu.SubItem, String> = buildLens(
        "name",
        { it.name },
        { p, v -> p.copy(name = v)}
)

public fun Menu.SubItem.Companion.icon(): Lens<Menu.SubItem, String> = buildLens(
        "icon",
        { it.icon },
        { p, v -> p.copy(icon = v)}
)

public fun Menu.SubItem.Companion.url(): Lens<Menu.SubItem, String> = buildLens(
        "url",
        { it.url },
        { p, v -> p.copy(url = v)}
)