package com.mthrsj.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_complex_calculator.*

class ComplexCalculator : AppCompatActivity() {

    private var operations: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_calculator)

        // Setting operation listeners
        plusButtonComplex.setOnClickListener { view -> addOperation(view) }
        minusButtonComplex.setOnClickListener { view -> addOperation(view) }
        divideButtonComplex.setOnClickListener { view -> addOperation(view) }
        multiplyButtonComplex.setOnClickListener { view -> addOperation(view) }
        clearButton.setOnClickListener { clearInput() }
        deleteButton.setOnClickListener { deleteLast() }
        equalButton.setOnClickListener { result() }

        // Setting number buttons listeners
        button0.setOnClickListener { view -> addNum(view) }
        button1.setOnClickListener { view -> addNum(view) }
        button2.setOnClickListener { view -> addNum(view) }
        button3.setOnClickListener { view -> addNum(view) }
        button4.setOnClickListener { view -> addNum(view) }
        button5.setOnClickListener { view -> addNum(view) }
        button6.setOnClickListener { view -> addNum(view) }
        button7.setOnClickListener { view -> addNum(view) }
        button8.setOnClickListener { view -> addNum(view) }
        button9.setOnClickListener { view -> addNum(view) }
    }

    private fun addOperation(v: View) {
        var op = v.tag.toString()
        try {
            operations[operations.size-1].toDouble()
            Log.d("OPER", "$op added to operations")
            when (op) {
                "plus" -> operations.add("+")
                "minus" -> operations.add("-")
                "divide" -> operations.add("/")
                "multiply" -> operations.add("x")
            }
            formatInput()
        }catch(e: NumberFormatException){

        }
    }

    private fun addNum(v: View) {
        var tag = v.tag.toString()
        var number = tag[tag.length - 1].toString().toDouble()
        Log.d("OPER", "button$number got clicked")

        try {
            if (operations.isNotEmpty()) {
                var lastItem = operations[operations.size - 1].toDouble()
                lastItem = (lastItem * 10) + number
                operations[operations.size - 1] = lastItem.toString()
            } else {
                operations.add(number.toString())
            }
        } catch (e: NumberFormatException) {
            operations.add(number.toString())
        }
        formatInput()
    }

    private fun deleteLast() {
        if(operations.size > 0) {
            try {
                var string = operations[operations.size - 1]
                Log.d("DEB", "String length=${string.length}")
                if (string.length > 3) {
                    var result = string.dropLast(3)
                    operations[operations.size - 1] = result.toDouble().toString()
                } else {
                    operations.removeAt(operations.size - 1)
                }
            } catch (e: NumberFormatException) {
                operations.removeAt(operations.size - 1)
            }
            formatInput()
        }
    }

    private fun clearInput() {
        Log.d("OPER", "Input cleared")
        operations = mutableListOf()
        inputBox.setText("")
    }

    private fun formatInput() {
        var text = ""
        for (i in operations) {
            text = "$text$i "
        }
        inputBox.setText(text)
    }

    private fun result() {
        var result: Double = 1.0
        var last = -1.0
        var op = ""
        for (item in operations) {
            try {
                var value = item.toDouble()
                if (last != -1.0) {
                    try {
                        Log.d("CALC", "Operation: $last $op $value")
                        when (op) {
                            "+" -> result = last + value
                            "-" -> result = last - value
                            "/" -> result = last / value
                            "x" -> result = last * value
                        }
                        last = result
                    } catch (e: ArithmeticException) {
                        text_input_error.setText(R.string.zeroDivision)
                    }
                } else {
                    last = value
                }
            } catch (e: java.lang.NumberFormatException) {
                op = item
            }
        }
        inputBox.setText(result.toString())
        operations = mutableListOf()
        operations.add(result.toString())
    }
}


