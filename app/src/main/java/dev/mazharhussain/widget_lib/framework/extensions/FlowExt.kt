package dev.mazharhussain.widget_lib.framework.extensions

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.reflect.KProperty

operator fun <T> StateFlow<T>.getValue(obj: Any?, property: KProperty<*>) = value
operator fun <T> MutableStateFlow<T>.setValue(obj: Any?, property: KProperty<*>, value: T) = update { value }
