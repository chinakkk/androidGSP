package com.example.empty

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.roundToInt

class CartesianPainter(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    constructor(context: Context) : this(context, null)


    var xMin = 0.0
    var xMax = 0.0
    var yMin = 0.0
    var yMax = 0.0
    var graph = false
    var bgPaint = Paint()
    var plane = CoordinatesConverter(width, height, xMin, xMax, yMin, yMax)
    val fgPaint = Paint()
    val grPaint = Paint()
    var pts: MutableList<Pair<Double, Double>> = mutableListOf()
    var j: Double = 0.0

    fun init(
        xMin1: Double,
        xMax1: Double,
        yMin1: Double,
        yMax1: Double,
        color_osey: Int,
        color_graph: Int,
        size_line: Float,
        graph1: Boolean,
        f: (Double) -> Double,
        _f: (Double) -> Double
    ) {
        bgPaint.color = 0xffffffd8.toInt()
        xMin = xMin1
        xMax = xMax1
        yMin = yMin1
        yMax = yMax1
        plane = CoordinatesConverter(width, height, xMin, xMax, yMin, yMax)
        fgPaint.color = color_osey
        fgPaint.strokeWidth = size_line
        grPaint.strokeWidth = size_line
        graph = graph1
        if (!graph) {
            pts.clear()
            gr(xMin, xMax, f)
        } else {
            pts.clear()
            gr(xMin, xMax, f)
            pts.reverse()
            gr(xMin, xMax, _f)
        }

        grPaint.color = color_graph
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        with(plane) {
            width = this@CartesianPainter.width
            height = this@CartesianPainter.height
        }
        canvas?.apply {
            drawPaint(bgPaint)
            val widthLine = plane.xCrt2Scr(0.0)
            val heightLine = plane.yCrt2Scr(0.0)
            drawLine(widthLine.toFloat(), 0F, widthLine.toFloat(), height.toFloat(), fgPaint)
            drawLine(0F, heightLine.toFloat(), width.toFloat(), heightLine.toFloat(), fgPaint)

            j = if (!graph)
                (xMax - xMin) / 0.1 - 1
            else (xMax - xMin)*2 / 0.1 - 2

            for (i in 0..j.toInt()) {
                val p1X = plane.xCrt2Scr(pts[i].first)
                val p1Y = plane.yCrt2Scr(pts[i].second)
                val p2X = plane.xCrt2Scr(pts[i + 1].first)
                val p2Y = plane.yCrt2Scr(pts[i + 1].second)
                drawLine(p1X.toFloat(), p1Y.toFloat(), p2X.toFloat(), p2Y.toFloat(), grPaint)
            }

        }
    }


    private fun gr(xMin: Double, xMax: Double, f: (Double) -> Double) {
        var x = xMin
        val h = 0.1
        while (x <= xMax) {
            val y = f(x)
            pts.add(Pair(x, y))
            x = ((x + h) * 10.0).roundToInt() / 10.0
        }
    }
}

