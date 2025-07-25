package dev.mazharhussain.widget_lib.framework.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.Widget
import dev.mazharhussain.widget_lib.framework.toSize

class Rectangle(size: Size) : Widget(size) {

    constructor(size: Length): this(size.toSize())
    constructor(width: Length, height: Length) : this(Size(width, height))

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        canvas.drawRect(bounds, Paint().apply { color = foregroundColor })
    }
}