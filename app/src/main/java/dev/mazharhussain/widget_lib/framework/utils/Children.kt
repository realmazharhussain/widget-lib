package dev.mazharhussain.widget_lib.framework.utils

import dev.mazharhussain.widget_lib.framework.Widget
import dev.mazharhussain.widget_lib.framework.layouts.Layout
import kotlinx.coroutines.flow.update

sealed interface Children : List<Widget> {
    fun add(child: Widget): Boolean
    operator fun plusAssign(child: Widget)
}

@Suppress("JavaDefaultMethodsNotOverriddenByDelegation")
private class ChildrenImpl(
    val parent: Layout,
    val list: MutableList<Widget> = mutableListOf(),
): Children, List<Widget> by list {

    override fun add(child: Widget) = list.add(child).also {
        child.parent = parent
        parent.childrenRedrawTrigger.update { list.map { it } }
    }
    override operator fun plusAssign(child: Widget) = add(child).let {}
}

fun Layout.children(): Children = ChildrenImpl(parent = this)
