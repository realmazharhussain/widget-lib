package dev.mazharhussain.widget_lib.framework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.annotation.CallSuper
import androidx.annotation.ColorInt
import dev.mazharhussain.widget_lib.framework.extensions.getValue
import dev.mazharhussain.widget_lib.framework.extensions.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

abstract class Widget(size: Size) {
    constructor(width: Length, height: Length): this(Size(width, height))

    private val _size = MutableStateFlow(size)
    var size by _size

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

    open val visibility: StateFlow<VisibilityState>?
        get() = topLevel?.visibility

    private val _backgroundColor = MutableStateFlow(Color.TRANSPARENT)
    var backgroundColor by _backgroundColor

    private val _foregroundColor = MutableStateFlow(Color.BLACK)
    var foregroundColor by _foregroundColor

    protected val _needsRedraw = MutableStateFlow(true)
    open val needsRedraw: Flow<Boolean> = _needsRedraw.asStateFlow()

    open val redrawTriggers: List<Flow<*>> = listOf(_size, _backgroundColor, _foregroundColor, _parent)

    init {
        @Suppress("OPT_IN_USAGE")
        GlobalScope.launch {
            topLevelFlow.collectLatest {
                val topLevel = it ?: return@collectLatest
                redrawTriggers.map {
                    topLevel.requireCoroutineScope().async { it.collectLatest { _needsRedraw.update { true } } }
                }.forEach { deferred -> deferred.await() }
            }
        }
    }

    @CallSuper
    open fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        _needsRedraw.update { false }
    }

    open fun onDrawBackground(context: Context, bounds: Rect, canvas: Canvas) {
        if (backgroundColor != Color.TRANSPARENT) {
            canvas.drawRect(bounds, Paint().apply { color = backgroundColor })
        }
    }
}
