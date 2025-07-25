package dev.mazharhussain.widget_lib.framework.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.Widget
import dev.mazharhussain.widget_lib.framework.dp
import dev.mazharhussain.widget_lib.framework.extensions.getValue
import dev.mazharhussain.widget_lib.framework.extensions.setValue
import dev.mazharhussain.widget_lib.framework.toPx
import kotlinx.coroutines.flow.MutableStateFlow

class Text(size: Size) : Widget(size) {

    constructor(width: Length, height: Length) : this(Size(width = width, height = height))

    private val _text = MutableStateFlow("")
    private val _textSize = MutableStateFlow(20.dp)

    var text by _text
    var textSize by _textSize

    override val redrawTriggers = super.redrawTriggers + listOf(_text, _textSize)

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        if (text.isNotBlank()) {
            canvas.drawText(
                /* text = */ text,
                /* x = */ bounds.left.toFloat(),
                /* y = */ bounds.exactCenterY(),
                /* paint = */ Paint().apply {
                    color = foregroundColor
                    textSize = this@Text.textSize.toPx(density = context.resources.displayMetrics.density)
                }
            )
        }

        super.onDrawForeground(context, bounds, canvas)
    }
}