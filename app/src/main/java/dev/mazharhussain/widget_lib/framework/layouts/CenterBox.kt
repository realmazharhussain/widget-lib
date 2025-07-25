package dev.mazharhussain.widget_lib.framework.layouts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.toPx
import dev.mazharhussain.widget_lib.framework.utils.Child

class CenterBox(size: Size) : Layout(size) {

    constructor(width: Length, height: Length) : this(Size(width, height))

    var child by Child()

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        val child = child ?: return

        val density = context.resources.displayMetrics.density
        val width = width.toPx(density, space = bounds.width()).coerceAtMost(bounds.width())
        val height = height.toPx(density, space = bounds.height()).coerceAtMost(bounds.height())

        val childHeight = child.height.toPx(density, space = height).coerceAtMost(bounds.height())
        val childWidth = child.width.toPx(density, space = width).coerceAtMost(bounds.width())
        val horizontalPadding = (width - childWidth) / 2
        val verticalPadding = (height - childHeight) / 2

        val childBounds = Rect(
            /* left = */ bounds.left + horizontalPadding,
            /* top = */ bounds.top + verticalPadding,
            /* right = */ bounds.right - horizontalPadding,
            /* bottom = */ bounds.bottom - verticalPadding,
        )

        child.onDrawBackground(context, childBounds, canvas)
        child.onDrawForeground(context, childBounds, canvas)
        super.onDrawForeground(context, bounds, canvas)
    }
}