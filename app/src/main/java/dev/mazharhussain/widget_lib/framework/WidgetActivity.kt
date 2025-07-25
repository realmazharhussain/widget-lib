package dev.mazharhussain.widget_lib.framework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import dev.mazharhussain.widget_lib.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            content.visibility.filter { it != VisibilityState.INVISIBLE }.collectLatest {
                content.needsRedraw.filter { it }.collectLatest {
                    view.triggerDraw()
                }
            }
        }
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
