package com.example.graphicstest.framework.foundation.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.graphicstest.framework.foundation.Length
import com.example.graphicstest.framework.foundation.Size
import com.example.graphicstest.framework.foundation.Widget
import com.example.graphicstest.framework.foundation.dp
import com.example.graphicstest.framework.foundation.toPx

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