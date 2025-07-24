package dev.mazharhussain.widget_lib.framework.foundation.layouts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.foundation.Length
import dev.mazharhussain.widget_lib.framework.foundation.Size
import dev.mazharhussain.widget_lib.framework.foundation.Widget
import dev.mazharhussain.widget_lib.framework.foundation.toPx

class Row(size: Size) : Layout(size) {
    constructor(width: Length, height: Length): this(Size(width, height))

    val children = mutableListOf<Widget>()

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        val density = context.resources.displayMetrics.density
        val width = width.toPx(density, space = bounds.width())
        val height = height.toPx(density, space = bounds.height())
        var horizontalOffset = 0

        for (child in children) {
            if (horizontalOffset >= width) {
                break
            }

            val childHeight = child.height.toPx(density, space = height)
            val childWidth = child.width.toPx(density, space = width - horizontalOffset)
            val childBounds = Rect(
                /* left = */ bounds.left + horizontalOffset,
                /* top = */ bounds.top,
                /* right = */ (bounds.left + horizontalOffset + childWidth).coerceAtMost(bounds.right),
                /* bottom = */ (bounds.top + childHeight).coerceAtMost(bounds.bottom)
            )
            child.onDrawBackground(context, childBounds, canvas)
            child.onDrawForeground(context, childBounds, canvas)

            horizontalOffset += childWidth
        }
    }

    @Deprecated("", ReplaceWith("children += child"))
    fun addChild(child: Widget) {
        children.add(child)
    }
}