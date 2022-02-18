package com.example.empty

class CoordinatesConverter(
    var width:   Int,
    var height:  Int,
    var xMin: Double,
    var xMax: Double,
    var yMin: Double,
    var yMax: Double
) {





    fun xCrt2Scr(x: Double): Double {
        return width / (xMax - xMin) * (x - xMin)
    }
    fun yCrt2Scr(y: Double): Double {
        return height / (yMax - yMin) * (yMax - y)
    }

}

