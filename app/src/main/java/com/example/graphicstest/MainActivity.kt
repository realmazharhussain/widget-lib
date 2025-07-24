package com.example.graphicstest

import android.graphics.Color
import com.example.graphicstest.framework.foundation.Length
import com.example.graphicstest.framework.foundation.Size
import com.example.graphicstest.framework.foundation.TopLevel
import com.example.graphicstest.framework.foundation.WidgetActivity
import com.example.graphicstest.framework.foundation.dp
import com.example.graphicstest.framework.foundation.layouts.CenterBox
import com.example.graphicstest.framework.foundation.layouts.Column
import com.example.graphicstest.framework.foundation.layouts.Row
import com.example.graphicstest.framework.foundation.widgets.Circle
import com.example.graphicstest.framework.foundation.widgets.Rectangle
import com.example.graphicstest.framework.foundation.widgets.Text
import com.example.graphicstest.framework.foundation.widgets.Triangle


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
