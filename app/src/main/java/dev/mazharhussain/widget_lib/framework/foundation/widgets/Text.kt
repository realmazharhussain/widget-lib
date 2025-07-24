package dev.mazharhussain.widget_lib.framework.foundation.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.foundation.Length
import dev.mazharhussain.widget_lib.framework.foundation.Size
import dev.mazharhussain.widget_lib.framework.foundation.Widget
import dev.mazharhussain.widget_lib.framework.foundation.dp
import dev.mazharhussain.widget_lib.framework.foundation.toPx

class Text(size: Size) : Widget(size) {

    constructor(width: Length, height: Length) : this(Size(width = width, height = height))

    var text: String = ""
    var textSize: Length.Dp = 20.dp

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        if (text.isBlank()) return

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
}