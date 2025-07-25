package dev.mazharhussain.widget_lib.framework.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.Widget

class Triangle(size: Size) : Widget(size) {

    constructor(size: Length): this(Size(width = size, height = size))

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        val path = Path().apply {
            moveTo(bounds.exactCenterX(), bounds.top.toFloat())
            lineTo(bounds.right.toFloat(), bounds.bottom.toFloat())
            lineTo(bounds.left.toFloat(), bounds.bottom.toFloat())
            close()
        }
        canvas.drawPath(path, Paint().apply { color = foregroundColor })
        super.onDrawForeground(context, bounds, canvas)
    }
}