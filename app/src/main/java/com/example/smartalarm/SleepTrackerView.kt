package com.example.smartalarm

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2

class SleepTrackerView(
    context: Context?,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    var listener: Listener? = null

    private val paint = Paint()
    private val paintEnd = Paint()
    private val paintWidth = 62f

    private val startAngle = 0f
    private val sweepAngle = 15f

    private val startColor = Color.rgb(42, 44, 62)
    private val markColor = Color.rgb(119, 106, 166)

    private var buttonStartClicked = -1
    private var buttonEndClicked = -1

    init {
        paint.style = Paint.Style.STROKE
        paint.color = startColor
        paint.strokeWidth = paintWidth

        paintEnd.style = Paint.Style.STROKE
        paintEnd.color = startColor
        paintEnd.strokeWidth = paintWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircleClock(canvas)
    }

    private fun drawCircleClock(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width.coerceAtMost(height) / 2f - paintWidth + 25

        for (i in 0 until 24) {
            paint.color = if (i == buttonStartClicked)
                markColor
            else startColor

            paintEnd.color = if (i == buttonEndClicked)
                markColor
            else startColor

            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + i * sweepAngle,
                sweepAngle,
                false,
                paint
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val centerX = width / 2f
        val centerY = height / 2f
        val x = event?.x
        val y = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val angle = (Math.toDegrees(
                    atan2(
                        y!! - centerY,
                        x!! - centerX
                    ).toDouble()
                ) + 360) % 360
                Log.d("MyLog", "$angle")
                buttonStartClicked = (angle / (360 / 24)).toInt()
                listener?.onClick(buttonStartClicked, buttonEndClicked)
                invalidate()
            }
        }
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                val angle = (Math.toDegrees(
                    atan2(
                        y!! - centerY,
                        x!! - centerX
                    ).toDouble()
                ) + 360) % 360
                Log.d("MyLog", "$angle")
                buttonEndClicked = (angle / (360 / 24)).toInt()
                listener?.onClick(buttonEndClicked, buttonStartClicked)
                invalidate()
            }
        }

        return true
    }

    interface Listener {
        fun onClick(firstIndex: Int, secondIndex: Int)
        abstract fun WheelDatePicker(any: Any)
    }
}