package dev.mazharhussain.widget_lib.framework

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import dev.mazharhussain.widget_lib.framework.utils.Child
import dev.mazharhussain.widget_lib.framework.utils.asMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

private val topLevelsMutable = mutableMapOf<String, TopLevel>()
val topLevels = topLevelsMutable.asMap()

@OptIn(ExperimentalCoroutinesApi::class)
abstract class TopLevel(val id: String) : Widget(size = Size.Fill), DefaultLifecycleObserver {
    private var activityRef = WeakReference<WidgetActivity>(null)
    private val _coroutineScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Default.limitedParallelism(parallelism = 1)
    )
    private val visibilityEvents = Channel<Pair<LifecycleOwner, Lifecycle.Event>>()
    private val activityLifecycleObserver = object : LifecycleEventObserver {
        var state = Lifecycle.State.DESTROYED
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (!(source as Activity).isChangingConfigurations && (event.targetState > state || event.isDestructive)) {
                state = event.targetState
                _coroutineScope.launch { visibilityEvents.send(element = source to event) }
            }
        }
    }

    var child by Child()

    override val visibility = visibilityEvents.consumeAsFlow().onEach {
        val (activity, event) = it
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate(owner = activity)
            Lifecycle.Event.ON_START -> onStart(owner = activity)
            Lifecycle.Event.ON_RESUME -> onResume(owner = activity)
            Lifecycle.Event.ON_PAUSE -> onPause(owner = activity)
            Lifecycle.Event.ON_STOP -> onStop(owner = activity)
            Lifecycle.Event.ON_DESTROY -> onDestroy(owner = activity)
            Lifecycle.Event.ON_ANY -> {}
        }
    }.map {
        it.second.targetState.toVisibilityState()
    }.stateIn(scope = _coroutineScope, started = SharingStarted.Companion.Eagerly, initialValue = VisibilityState.INVISIBLE)

    init {
        backgroundColor = Color.LTGRAY
    }

    fun setActivity(activity: WidgetActivity) {
        if (activityRef.get() === activity) return
        activity.lifecycle.addObserver(activityLifecycleObserver)
        activityRef = WeakReference(activity)
    }

    fun unsetActivity(activity: WidgetActivity) {
        if (activityRef.get() !== activity) return
        activity.lifecycle.removeObserver(activityLifecycleObserver)
        activityRef = WeakReference(null)
    }

    fun requireCoroutineScope() = _coroutineScope

    override fun onCreate(owner: LifecycleOwner) {
        topLevelsMutable[id] = this
        super.onCreate(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        topLevelsMutable.remove(key = id)
    }

    override fun onDrawForeground(context: Context, bounds: Rect, canvas: Canvas) {
        child?.onDrawBackground(context, bounds, canvas)
        child?.onDrawForeground(context, bounds, canvas)
    }
}

val Lifecycle.Event.isDestructive get() = when(this) {
    Lifecycle.Event.ON_PAUSE -> true
    Lifecycle.Event.ON_STOP -> true
    Lifecycle.Event.ON_DESTROY -> true
    else -> false
}
