package dev.mazharhussain.widget_lib.framework.layouts

import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.Widget
import kotlinx.coroutines.flow.MutableStateFlow

abstract class Layout(size: Size) : Widget(size) {
    val childrenRedrawTrigger = MutableStateFlow<Any?>(null)
    override val redrawTriggers get() = super.redrawTriggers + listOf(childrenRedrawTrigger)
}
