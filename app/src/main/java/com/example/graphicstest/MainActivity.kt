package com.example.graphicstest

import com.example.graphicstest.framework.foundation.TopLevel
import com.example.graphicstest.framework.foundation.WidgetActivity


class MainActivity : WidgetActivity() {
    override fun content() = TopLevel(id = "main")
}
