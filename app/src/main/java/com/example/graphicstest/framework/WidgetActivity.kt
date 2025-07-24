package com.example.graphicstest.framework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class WidgetActivity : AppCompatActivity() {
    lateinit var content: TopLevel; private set
    abstract fun content(): TopLevel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentId = savedInstanceState?.getString("content_id")
        content = when (contentId) {
            null -> content()
            else -> topLevels[contentId] ?: throw IllegalStateException("A top level with id '$contentId' not found")
        }
        content.setActivity(activity = this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("content_id", content.id)
    }

    override fun onDestroy() {
        super.onDestroy()
        content.unsetActivity(activity = this)
    }
}
