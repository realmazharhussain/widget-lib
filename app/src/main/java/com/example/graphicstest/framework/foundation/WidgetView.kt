package com.example.graphicstest.framework.foundation

import android.content.Context
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class WidgetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : SurfaceView(context, attrs, defStyleAttr, defStyleRes), SurfaceHolder.Callback {

    var content: TopLevel? = null

    init {
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.RGBA_8888)
        holder.addCallback(this)
    }

    private fun drawWithBounds(canvas: Canvas, draw: TopLevel.(bounds: Rect) -> Unit) {
        val content = content ?: return
        val width = content.width.toPx(density = context.resources.displayMetrics.density, space = canvas.width)
        val height = content.height.toPx(density = context.resources.displayMetrics.density, space = canvas.height)
        val bounds = Rect(/* left = */ 0, /* top = */ 0, /* right = */ width, /* bottom = */ height)
        content.draw(bounds)
    }

    override fun onDrawForeground(canvas: Canvas) = drawWithBounds(canvas) { bounds ->
        onDrawForeground(context, bounds, canvas)
    }

    override fun onDraw(canvas: Canvas) = drawWithBounds(canvas) {
        onDrawBackground(context, bounds = it, canvas)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        triggerDraw()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        triggerDraw()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    private fun triggerDraw() {
        val canvas = holder.lockHardwareCanvas()
        draw(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

}
