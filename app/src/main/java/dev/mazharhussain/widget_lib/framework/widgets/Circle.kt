package dev.mazharhussain.widget_lib.framework.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.graphics.toRectF
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Widget

class Circle(size: Length) : Widget(size) {
    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        canvas.drawArc(bounds.toRectF(), 0f, 360f, true, Paint().apply { color = foregroundColor })
    }
}
