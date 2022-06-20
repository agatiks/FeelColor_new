package com.example.myapplication

import android.graphics.Color
import kotlin.math.pow
import kotlin.math.sqrt

class ColoredObject(private var r: Int, private var g: Int, private var b: Int){

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
            return dE(other) > 2.3
        }
        return false
    }

    fun dE(other: ColoredObject): Double {
        val Lab1 = Lab(this)
        val Lab2 = Lab(other)
        return sqrt((Lab1.L - Lab2.L).pow(2) +
                (Lab1.a - Lab2.a).pow(2) +
                (Lab1.b - Lab2.b).pow(2))
    }

    fun getColor(): Int {
        return Color.rgb(r, g, b)
    }

    companion object {
        private fun randomColor(): Int {
            return (0..255).shuffled().first()
        }
    }

    inner class Lab(obj: ColoredObject) {
        private val MAX = 255
        val L: Double
        val a: Double
        val b: Double

        init {
            L = 116 * f(obj.g) - 16
            a = 500 * (f(obj.r) - f(obj.g))
            b = 200 * (f(obj.g) - f(obj.b))
        }

        private fun f(color: Int): Double {
            val t: Double = color.toDouble() / MAX
            val const: Double = 6.0 / 29.0
            if(t > const.pow(3)) {
                return t.pow(1/3)
            } else {
                return 1.0/3.0 * (1.0 / const).pow(2) * t + 4.0/29.0
            }
        }
    }

}