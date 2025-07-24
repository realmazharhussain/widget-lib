package com.example.graphicstest

import com.example.graphicstest.framework.TopLevel
import com.example.graphicstest.framework.WidgetActivity


class MainActivity : WidgetActivity() {
    override fun content() = TopLevel(id = "main")
}
