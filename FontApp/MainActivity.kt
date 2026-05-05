package com.example.practiceapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        val radioGroupCase = findViewById<RadioGroup>(R.id.radioGroupCase)
        val checkBold = findViewById<CheckBox>(R.id.checkBold)
        val checkItalic = findViewById<CheckBox>(R.id.checkItalic)
        val btnApply = findViewById<Button>(R.id.btnApply)

        btnApply.setOnClickListener {
            var enteredText = editText.text.toString().trim()

            if (enteredText.isEmpty()) {
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            when (radioGroupCase.checkedRadioButtonId) {
                R.id.radioUppercase -> {
                    enteredText = enteredText.uppercase(Locale.getDefault())
                    textView.setTextColor(Color.BLACK)
                }
                R.id.radioLowercase -> {
                    enteredText = enteredText.lowercase(Locale.getDefault())
                    textView.setTextColor(Color.BLACK)
                }
                R.id.radioredcolor -> {
                    textView.setTextColor(Color.RED)
                }
                R.id.radiobluecolor -> {
                    textView.setTextColor(Color.BLUE)
                }
                else -> {
                    textView.setTextColor(Color.BLACK)
                }
            }

            textView.text = enteredText


            val style = when {
                checkBold.isChecked && checkItalic.isChecked -> Typeface.BOLD_ITALIC
                checkBold.isChecked -> Typeface.BOLD
                checkItalic.isChecked -> Typeface.ITALIC
                else -> Typeface.NORMAL
            }

            textView.setTypeface(null, style)
        }
    }
}
