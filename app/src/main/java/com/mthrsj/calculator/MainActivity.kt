package com.mthrsj.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusButton.setOnClickListener { operation("plus") }
        minusButton.setOnClickListener { operation("minus") }
        divideButton.setOnClickListener { operation("divide") }
        multiplyButton.setOnClickListener { operation("multiply") }
    }
    private fun operation(type: String) {
        val value1 = input1.text.toString().toDouble()
        val value2 = input2.text.toString().toDouble()
        calculate(type, value1, value2)
    }

    private fun calculate(type: String, value1: Double, value2: Double) {
        Log.d("TYPE", type)
        if (type == "plus") {
            val result = value1 + value2
            Log.d("CALC", "$result")
            resultText.text = result.toString()
        } else if (type == "minus") {
            val result = value1 - value2
            Log.d("CALC", "$result")
            resultText.text = result.toString()
        } else if (type == "divide") {
            if (value2 != 0.0) {
                val result = value1 / value2
                Log.d("CALC", "$result")
                resultText.text = result.toString()
            }
        } else if (type === "multiply") {
            val result = value1 * value2
            Log.d("CALC", "$result")
            resultText.text = result.toString()
        }

    }
}
