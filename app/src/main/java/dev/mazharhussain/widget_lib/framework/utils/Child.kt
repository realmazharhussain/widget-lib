package dev.mazharhussain.widget_lib.framework.utils

import dev.mazharhussain.widget_lib.framework.Widget
import dev.mazharhussain.widget_lib.framework.layouts.Layout
import kotlinx.coroutines.flow.update
import kotlin.reflect.KProperty

class Child {
    private var value: Widget? = null
    operator fun getValue(parent: Layout, property: KProperty<*>) = value
    operator fun setValue(parent: Layout, property: KProperty<*>, value: Widget?) {
        this.value?.parent = null
        this.value = value
        this.value?.parent = parent
        parent.childrenRedrawTrigger.update { value }
    }
}
