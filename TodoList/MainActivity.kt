package com.example.todolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        return DatePickerDialog(
            requireContext(), this,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = "$day/${month + 1}/$year"
        (requireActivity() as MainActivity).onDateSelected(date)
    }
}


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        return TimePickerDialog(
            activity, this,
            c.get(Calendar.HOUR_OF_DAY),
            c.get(Calendar.MINUTE),
            DateFormat.is24HourFormat(activity)
        )
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val time = "$hourOfDay:${String.format("%02d", minute)}"
        (requireActivity() as MainActivity).onTimeSelected(time)
    }
}

class MainActivity : AppCompatActivity() {

    private var date = ""
    private var time = ""

    private lateinit var btnDate: Button
    private lateinit var btnTime: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etTask = findViewById<EditText>(R.id.etTask)
        val tvList = findViewById<TextView>(R.id.tvList)
        btnDate    = findViewById(R.id.btnDate)
        btnTime    = findViewById(R.id.btnTime)

        btnDate.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "datePicker")
        }

        btnTime.setOnClickListener {
            TimePickerFragment().show(supportFragmentManager, "timePicker")
        }


        findViewById<Button>(R.id.btnAdd).setOnClickListener {

            val task = etTask.text.toString()

            tvList.append("• $task — $date  $time\n")
        }
    }




    fun onDateSelected(selectedDate: String) {
        date = selectedDate
        btnDate.text = date
    }

    fun onTimeSelected(selectedTime: String) {
        time = selectedTime
        btnTime.text = time
    }
}
