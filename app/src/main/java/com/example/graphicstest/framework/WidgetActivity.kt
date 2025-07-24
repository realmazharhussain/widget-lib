package com.example.graphicstest.framework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.graphicstest.R

abstract class WidgetActivity : AppCompatActivity() {
    lateinit var content: TopLevel; private set
    abstract fun content(): TopLevel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_widget)
        val view = findViewById<WidgetView>(R.id.widget_view)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contentId = savedInstanceState?.getString("content_id")
        content = when (contentId) {
            null -> content()
            else -> topLevels[contentId] ?: throw IllegalStateException("A top level with id '$contentId' not found")
        }
        content.setActivity(activity = this)
        view.content = content
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
