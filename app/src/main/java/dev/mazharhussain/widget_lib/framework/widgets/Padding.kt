package dev.mazharhussain.widget_lib.framework.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Widget

class Padding(size: Length.Dp) : Widget(size) {
    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {}
}