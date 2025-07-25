package dev.mazharhussain.widget_lib.framework

import kotlin.math.roundToInt

data class Size(
    val width: Length,
    val height: Length,
) {
    companion object {
        val Fill = Size(width = Length.Fill, height = Length.Fill)
        val Zero = Size(width = 0.dp, height = 0.dp)
    }
}

sealed interface Length {
    object Fill : Length
    data class Dp(val dp: Int) : Length
}

val Int.dp get() = Length.Dp(dp = this)
fun Length.Dp.toPx(density: Float) = dp * density
fun Length.Dp.roundToPx(density: Float) = toPx(density).roundToInt()

fun Length.toPx(density: Float, space: Int) = when (this) {
    is Length.Dp -> roundToPx(density)
    Length.Fill -> space
}
