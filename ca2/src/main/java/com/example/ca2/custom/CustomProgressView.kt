package com.example.ca2.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.ca2.R
import kotlin.math.min

class CustomProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress: Float = 0f
    private var progressText: String = "0%"
    private var progressColor: Int = 0xFF6200EA.toInt()
    private var strokeWidth: Float = 8f

    private val progressPaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    private val backgroundPaint = Paint().apply {
        color = 0xFFE0E0E0.toInt()
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressView)
        progressColor = typedArray.getColor(R.styleable.CustomProgressView_progressColor, progressColor)
        strokeWidth = typedArray.getDimension(R.styleable.CustomProgressView_strokeWidth, strokeWidth)
        typedArray.recycle()

        progressPaint.strokeWidth = strokeWidth
        backgroundPaint.strokeWidth = strokeWidth
        textPaint.textSize = 40f
        textPaint.color = progressColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (min(width, height) / 2f) - strokeWidth

        canvas.drawCircle(centerX, centerY, radius, backgroundPaint)

        val sweepAngle = (progress / 100f) * 360f
        progressPaint.color = progressColor
        progressPaint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius,
            -90f,
            sweepAngle,
            false,
            progressPaint
        )

        // Draw text
        canvas.drawText(progressText, centerX, centerY + 20f, textPaint)
    }

    fun setProgress(progress: Float) {
        this.progress = progress.coerceIn(0f, 100f)
        invalidate()
    }

    fun setProgressText(text: String) {
        this.progressText = text
        invalidate()
    }
}
