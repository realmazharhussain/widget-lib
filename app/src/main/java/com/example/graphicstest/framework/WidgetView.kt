package com.example.graphicstest.framework

import android.content.Context
import android.graphics.Canvas
import android.graphics.PixelFormat
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

    override fun onDraw(canvas: Canvas) {
        val content = content ?: return
        canvas.drawColor(content.backgroundColor)
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
