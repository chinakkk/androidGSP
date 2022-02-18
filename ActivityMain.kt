package com.example.empty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    var xMin: Double = 0.0
    var xMax: Double = 0.0
    var yMin: Double = 0.0
    var yMax: Double = 0.0
    var color_osey = 0xffff0000.toInt()
    var color_graph = 0xffff0000.toInt()
    var size_line = 10F
    var graph = false
    var f: (Double) -> Double = ::f1
    var _f: (Double) -> Double = ::f3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        xMin = intent.getDoubleExtra("xMin", -10.0)
        xMax = intent.getDoubleExtra("xMax", 10.0)
        yMin = intent.getDoubleExtra("yMin", -10.0)
        yMax = intent.getDoubleExtra("yMax", 10.0)
        when (intent.getIntExtra("color_osey", 0xffff0000.toInt())) {
            R.id.os_red -> color_osey = 0xffff0000.toInt()
            R.id.os_green -> color_osey = 0xff00ff00.toInt()
            R.id.os_yellow -> color_osey = 0xffFFEB3B.toInt()
        }
        when (intent.getIntExtra("color_graph", 0xffff0000.toInt())) {
            R.id.graph_red -> color_graph = 0xffff0000.toInt()
            R.id.graph_green -> color_graph = 0xff00ff00.toInt()
            R.id.graph_yellow -> color_graph = 0xffFFEB3B.toInt()
        }

        when (intent.getIntExtra("size_line", 10)) {
            R.id.small -> size_line = 7F
            R.id.big -> size_line = 14F
        }
        graph = intent.getBooleanExtra("graph", false)
        if (graph) f = ::f2

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment(xMin, xMax, yMin, yMax, color_osey, color_graph, size_line, graph, f, _f))
                .commitNow()
        }
    }

    fun f1(x: Double) = exp(x) / x.pow(3) - sin(x).pow(3)
    fun f2(x: Double) = sqrt(16 - 16 * x.pow(2) / 25)
    fun f3(x: Double) = -sqrt(16 - 16 * x.pow(2) / 25)
}
