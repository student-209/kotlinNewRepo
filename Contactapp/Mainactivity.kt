
package com.example.contactapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val contactList = arrayListOf(
        Contact("Rutuja Mane",  "+91 98765 43210", R.drawable.img),
        Contact("Vaishnavi Bhosale",   "+91 91234 56789", R.drawable.img_1),
        Contact("Diksha Khade ",   "+91 87654 32109", R.drawable.img_2),
        Contact("Kiran Ghorpade  ",   "+91 87949473839", R.drawable.img_3),



        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactAdapter(contactList)
    }
}


