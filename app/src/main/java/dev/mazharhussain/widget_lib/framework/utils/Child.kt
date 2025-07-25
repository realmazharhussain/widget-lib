package dev.mazharhussain.widget_lib.framework.utils

import dev.mazharhussain.widget_lib.framework.Widget
import kotlin.reflect.KProperty

class Child {
    private var value: Widget? = null
    operator fun getValue(parent: Widget, property: KProperty<*>) = value
    operator fun setValue(parent: Widget, property: KProperty<*>, value: Widget?) {
        this.value?.parent = null
        this.value = value
        this.value?.parent = parent
    }
}
