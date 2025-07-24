package com.example.graphicstest.framework.foundation.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.graphics.toRectF
import com.example.graphicstest.framework.foundation.Length
import com.example.graphicstest.framework.foundation.Widget

class Circle(size: Length) : Widget(width = size, height = size) {
    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        canvas.drawArc(bounds.toRectF(), 0f, 360f, true, Paint().apply { color = foregroundColor })
    }
}
