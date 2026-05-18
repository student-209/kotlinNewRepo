package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "TodoDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "CREATE TABLE tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "task TEXT," +
                    "date TEXT," +
                    "time TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS tasks")
        onCreate(db)
    }

    // INSERT
    fun insertTask(task: String, date: String, time: String): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put("task", task)
        values.put("date", date)
        values.put("time", time)

        val result = db.insert("tasks", null, values)

        return result != -1L
    }

    // READ
    fun getAllTasks(): ArrayList<Task> {

        val taskList = ArrayList<Task>()

        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM tasks", null)

        if (cursor.moveToFirst()) {

            do {

                val task = Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )

                taskList.add(task)

            } while (cursor.moveToNext())
        }

        cursor.close()

        return taskList
    }

    // UPDATE
    fun updateTask(id: Int, task: String): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put("task", task)

        val result = db.update(
            "tasks",
            values,
            "id=?",
            arrayOf(id.toString())
        )

        return result > 0
    }

    // DELETE
    fun deleteTask(id: Int): Boolean {

        val db = writableDatabase

        val result = db.delete(
            "tasks",
            "id=?",
            arrayOf(id.toString())
        )

        return result > 0
    }
}
