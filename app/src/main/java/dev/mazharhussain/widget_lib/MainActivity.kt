package dev.mazharhussain.widget_lib

import android.graphics.Color
import dev.mazharhussain.widget_lib.framework.Length
import dev.mazharhussain.widget_lib.framework.Size
import dev.mazharhussain.widget_lib.framework.TopLevel
import dev.mazharhussain.widget_lib.framework.WidgetActivity
import dev.mazharhussain.widget_lib.framework.dp
import dev.mazharhussain.widget_lib.framework.layouts.CenterBox
import dev.mazharhussain.widget_lib.framework.layouts.Column
import dev.mazharhussain.widget_lib.framework.layouts.Row
import dev.mazharhussain.widget_lib.framework.widgets.Circle
import dev.mazharhussain.widget_lib.framework.widgets.Padding
import dev.mazharhussain.widget_lib.framework.widgets.Rectangle
import dev.mazharhussain.widget_lib.framework.widgets.Text
import dev.mazharhussain.widget_lib.framework.widgets.Triangle


class MainActivity : WidgetActivity() {
    override fun content() = MyContent()
}

class MyContent : TopLevel(id = "main") {
    init { child = content() }

    fun content() = Column(Size.Fill).apply {
        children += Rectangle(size = 100.dp).apply { foregroundColor = Color.GREEN }
        children += Padding(size = 20.dp)
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
