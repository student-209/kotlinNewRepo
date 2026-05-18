package com.example.todolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var etTask: EditText
    lateinit var tvList: TextView

    lateinit var btnDate: Button
    lateinit var btnTime: Button

    lateinit var dbHelper: DBHelper

    var date = ""
    var time = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        etTask = findViewById(R.id.etTask)
        tvList = findViewById(R.id.tvList)

        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnShow = findViewById<Button>(R.id.btnShow)

        dbHelper = DBHelper(this)

        // DATE PICKER
        btnDate.setOnClickListener {

            val c = Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, day ->

                    date = "$day/${month + 1}/$year"
                    btnDate.text = date

                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // TIME PICKER
        btnTime.setOnClickListener {

            val c = Calendar.getInstance()

            TimePickerDialog(
                this,
                { _, hour, minute ->

                    time = "$hour:$minute"
                    btnTime.text = time

                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true
            ).show()
        }

        // INSERT
        btnAdd.setOnClickListener {

            val task = etTask.text.toString()

            if (task.isEmpty()) {

                Toast.makeText(this, "Enter Task", Toast.LENGTH_SHORT).show()

            } else {

                val result = dbHelper.insertTask(task, date, time)

                if (result) {

                    Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()

                    etTask.text.clear()

                } else {

                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // SHOW TASKS
        btnShow.setOnClickListener {

            val tasks = dbHelper.getAllTasks()

            tvList.text = ""

            for (t in tasks) {

                tvList.append(
                    "ID: ${t.id}\n" +
                            "Task: ${t.task}\n" +
                            "Date: ${t.date}\n" +
                            "Time: ${t.time}\n\n"
                )
            }
        }
    }
}
