package dev.mazharhussain.widget_lib

import android.graphics.Color
import dev.mazharhussain.widget_lib.framework.TopLevel
import dev.mazharhussain.widget_lib.framework.WidgetActivity


class MainActivity : WidgetActivity() {
    override fun content() = TopLevel(id = "main").apply {
        backgroundColor = Color.WHITE
    }
}
