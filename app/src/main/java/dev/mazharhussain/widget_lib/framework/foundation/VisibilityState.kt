package dev.mazharhussain.widget_lib.framework.foundation

import androidx.lifecycle.Lifecycle

enum class VisibilityState { INVISIBLE, PARTIALLY_VISIBLE, FULLY_VISIBLE }

fun Lifecycle.State.toVisibilityState() = when (this) {
    Lifecycle.State.DESTROYED -> VisibilityState.INVISIBLE
    Lifecycle.State.INITIALIZED -> VisibilityState.INVISIBLE
    Lifecycle.State.CREATED -> VisibilityState.INVISIBLE
    Lifecycle.State.STARTED -> VisibilityState.PARTIALLY_VISIBLE
    Lifecycle.State.RESUMED -> VisibilityState.FULLY_VISIBLE
}
