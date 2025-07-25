package dev.mazharhussain.widget_lib.framework.layouts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.Widget
import dev.mazharhussain.widget_lib.framework.toPx

class Column(size: Size) : Widget(size) {
    val children = mutableListOf<Widget>()

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        val density = context.resources.displayMetrics.density
        val width = width.toPx(density, space = bounds.width()).coerceAtMost(bounds.width())
        val height = height.toPx(density, space = bounds.height()).coerceAtMost(bounds.height())
        var verticalOffset = 0

        for (child in children) {
            if (verticalOffset >= height) {
                break
            }

            val childHeight = child.height.toPx(density, space = height - verticalOffset)
            val childWidth = child.width.toPx(density, space = width)
            val childBounds = Rect(
                /* left = */ bounds.left,
                /* top = */ bounds.top + verticalOffset,
                /* right = */ (bounds.left + childWidth).coerceAtMost(bounds.right),
                /* bottom = */ (bounds.top + verticalOffset + childHeight).coerceAtMost(bounds.bottom),
            )
            child.onDrawBackground(context, childBounds, canvas)
            child.onDrawForeground(context, childBounds, canvas)

            verticalOffset += childHeight
        }
    }

    @Deprecated("", ReplaceWith("children += child"))
    fun addChild(child: Widget) {
        children.add(child)
    }
}