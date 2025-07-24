package com.example.graphicstest.framework.foundation.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.graphicstest.framework.foundation.Length
import com.example.graphicstest.framework.foundation.Widget

class Rectangle(
    width: Length,
    height: Length,
) : Widget(width, height) {

    constructor(size: Length): this(width = size, height = size)

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        canvas.drawRect(bounds, Paint().apply { color = foregroundColor })
    }
}