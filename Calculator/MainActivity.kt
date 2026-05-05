package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var display: EditText

    var currentInput = ""
    var operator = ""
    var firstNumber = 0.0
    var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.txtDisplay)

        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener {
                val btn = findViewById<Button>(id)
                currentInput += btn.text.toString()
                expression += btn.text.toString()
                display.setText(expression)
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { chooseOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { chooseOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { chooseOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { chooseOperator("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentInput = ""
            operator = ""
            firstNumber = 0.0
            expression = ""
            display.setText("")
        }
    }

    fun chooseOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            val inputNumber = currentInput.toDouble()

            if (operator.isEmpty()) {
                firstNumber = inputNumber
            } else {
                val result = calculate(firstNumber, inputNumber, operator)
                if (result == null) {
                    display.setText("Cannot divide by zero")
                    resetAll()
                    return
                }
                firstNumber = result
            }

            operator = op
            expression += op
            currentInput = ""
            display.setText(expression)
        }
    }

    fun calculateResult() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val secondNumber = currentInput.toDouble()
            val result = calculate(firstNumber, secondNumber, operator)

            if (result == null) {
                display.setText("Cannot divide by zero")
                resetAll()
                return
            }

            expression += "=" + result.toString()
            display.setText(expression)

            currentInput = result.toString()
            firstNumber = result
            operator = ""
            expression = result.toString()
        }
    }

    fun calculate(a: Double, b: Double, op: String): Double? {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else null
            else -> null
        }
    }

    fun resetAll() {
        currentInput = ""
        operator = ""
        firstNumber = 0.0
        expression = ""
    }
}
