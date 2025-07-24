package com.example.graphicstest.framework

import android.graphics.Color
import androidx.annotation.ColorInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.lang.ref.WeakReference

abstract class Widget(var size: Size) {
    constructor(width: Length, height: Length): this(Size(width, height))

    var width
        get() = size.width
        set(value) { size = size.copy(width = value) }

    var height
        get() = size.height
        set(value) { size = size.copy(height = value) }

    private val _parent = MutableStateFlow(WeakReference<Widget>(null))
    val parentFlow: Flow<Widget?> = _parent.map { it.get() }
    var parent: Widget?
        get() = _parent.value.get()
        set(value) = _parent.update { WeakReference(value) }

    @OptIn(ExperimentalCoroutinesApi::class)
    val topLevelFlow: Flow<TopLevel?> =
        if (this is TopLevel) flowOf(this)
        else parentFlow.flatMapLatest { it?.topLevelFlow ?: flowOf(null) }

    val topLevel: TopLevel?
        get() = this as? TopLevel ?: parent?.topLevel

    val coroutineScope: CoroutineScope?
        get() = topLevel?.requireCoroutineScope()

    open val visibility: StateFlow<VisibilityState>?
        get() = topLevel?.visibility

    @ColorInt
    var backgroundColor: Int = Color.LTGRAY
}
