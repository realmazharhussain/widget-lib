package com.example.graphicstest.framework.foundation

data class Size(
    val width: Length,
    val height: Length,
) {
    companion object {
        val Fill = Size(width = Length.Fill, height = Length.Fill)
        val WrapContent = Size(width = Length.WrapContent, height = Length.WrapContent)
    }
}

sealed interface Length {
    object Fill : Length
    object WrapContent : Length
    data class Dp(val dp: Int) : Length
}

val Int.dp get() = Length.Dp(dp = this)
