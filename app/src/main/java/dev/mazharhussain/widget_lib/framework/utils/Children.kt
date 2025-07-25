package dev.mazharhussain.widget_lib.framework.utils

import dev.mazharhussain.widget_lib.framework.Widget

sealed interface Children : List<Widget> {
    fun add(child: Widget): Boolean
    operator fun plusAssign(child: Widget)
}

@Suppress("JavaDefaultMethodsNotOverriddenByDelegation")
private class ChildrenImpl(
    val parent: Widget,
    val list: MutableList<Widget> = mutableListOf(),
): Children, List<Widget> by list {
    override fun add(child: Widget) = list.add(child).also { child.parent = parent }
    override operator fun plusAssign(child: Widget) = add(child).let {}
}

fun Widget.children(): Children = ChildrenImpl(parent = this)
