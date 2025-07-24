package dev.mazharhussain.widget_lib

import android.graphics.Color
import dev.mazharhussain.widget_lib.framework.foundation.Length
import dev.mazharhussain.widget_lib.framework.foundation.Size
import dev.mazharhussain.widget_lib.framework.foundation.TopLevel
import dev.mazharhussain.widget_lib.framework.foundation.WidgetActivity
import dev.mazharhussain.widget_lib.framework.foundation.dp
import dev.mazharhussain.widget_lib.framework.foundation.layouts.CenterBox
import dev.mazharhussain.widget_lib.framework.foundation.layouts.Column
import dev.mazharhussain.widget_lib.framework.foundation.layouts.Row
import dev.mazharhussain.widget_lib.framework.foundation.widgets.Circle
import dev.mazharhussain.widget_lib.framework.foundation.widgets.Rectangle
import dev.mazharhussain.widget_lib.framework.foundation.widgets.Text
import dev.mazharhussain.widget_lib.framework.foundation.widgets.Triangle


class MainActivity : WidgetActivity() {
    override fun content() = TopLevel(id = "main").apply {
        child = Column(Size.Fill).apply {
            children += Rectangle(size = 100.dp).apply { foregroundColor = Color.GREEN }
            children += Row(width = Length.Fill, height = 200.dp).apply {
                backgroundColor = Color.WHITE

                children += Rectangle(width = 100.dp, height = 200.dp).apply { foregroundColor = Color.YELLOW }
                children += CenterBox(width = 50.dp, height = Length.Fill).apply {
                    child = Circle(size = 50.dp).apply { foregroundColor = Color.CYAN }
                }
                children += Triangle(size = 200.dp).apply { foregroundColor = Color.MAGENTA }
            }
            children += Rectangle(width = Length.Fill, height = 100.dp).apply { foregroundColor = Color.RED }
            children += CenterBox(Size.Fill).apply {
                child = Text(width = 100.dp, height = Length.Fill).apply {
                    text = "Hello World"
                }
            }
        }
    }
}
