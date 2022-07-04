package com.example.myapplication

import android.graphics.Color
import kotlin.math.pow
import kotlin.math.sqrt

class ColoredObject(private var r: Int, private var g: Int, private var b: Int){
    private val MAX = 255

    constructor(): this(
        randomColor(),
        randomColor(),
        randomColor()
    )

    fun setR(r: Int) {
        this.r = r
    }
    fun setG(g: Int) {
        this.g = g
    }
    fun setB(b: Int) {
        this.b = b
    }

    fun getR() : Int{
        return r
    }
    fun getG() : Int {
        return g
    }
    fun getB() : Int {
        return b
    }


    override fun equals(other: Any?): Boolean {
        if(other is ColoredObject) {
            return dE(other) < 2.3
        }
        return false
    }

    override fun toString(): String {
        return "#" + Integer.toHexString(this.getColor())
    }

    fun dE(other: ColoredObject): Double {
        val Lab1 = this.toLab()
        val Lab2 = other.toLab()
        return sqrt((Lab1.L - Lab2.L).pow(2) +
                (Lab1.a - Lab2.a).pow(2) +
                (Lab1.b - Lab2.b).pow(2))
    }

    fun getColor(): Int {
        return Color.rgb(r, g, b)
    }

    private fun toLab(): Lab {
        return Lab(this)
    }

    companion object {
        private fun randomColor(): Int {
            return (0..255).shuffled().first()
        }
    }

    inner class XYZ{
        val X: Double
        val Y: Double
        val Z: Double
        constructor(X: Double,
                    Y: Double,
                    Z: Double) {
            this.X = X
            this.Y = Y
            this.Z = Z
        }

        constructor(obj: ColoredObject){
            var r = obj.r / MAX.toDouble()//todo: copypaste
            var g = obj.g / MAX.toDouble()
            var b = obj.b / MAX.toDouble()
            r = if(r > 0.04045) ((r + 0.055) / 1.055).pow(2.4) else r / 12.92
            g = if(g > 0.04045) ((g + 0.055) / 1.055).pow(2.4) else g / 12.92
            b = if(b > 0.04045) ((b + 0.055) / 1.055).pow(2.4) else b / 12.92
            r*=100
            g*=100
            b*=100
            X = r * 0.4124 + g * 0.3576 + b * 0.1805
            Y = r * 0.2126 + g * 0.7152 + b * 0.0722
            Z = r * 0.0193 + g * 0.1192 + b * 0.9505
        }

    }

    inner class Lab(obj: ColoredObject) {
        val xyz: XYZ = XYZ(obj)
        val L: Double
        val a: Double
        val b: Double
        private val WHITE = XYZ(95.047, 100.0, 108.883)

        init {
            val x = xyz.X / WHITE.X
            val y = xyz.Y / WHITE.Y
            val z = xyz.Z / WHITE.Z
            L = 116 * f(y) - 16
            a = 500 * (f(x) - f(y))
            b = 200 * (f(y) - f(z))
        }

        private fun f(t: Double): Double {
            val const: Double = 6.0 / 29.0
            if(t > const.pow(3)) {
                return t.pow(1.0/3.0)
            } else {
                return 1.0/3.0 * (1.0 / const).pow(2) * t + 4.0/29.0
            }
        }
    }

}