package com.example.secondpracticeapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class ListActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listView = findViewById(R.id.ListView)

        val contacts: MutableList<Contact> = mutableListOf(
            Contact("Петя", "123"),
            Contact("Вася", "456"),
            Contact("Иван", "789"),
        )

        listView.adapter = MyAdapter(contacts)
        listView.setOnItemClickListener { p1, p2, idx, p4 ->
//            Toast.makeText(
//                this,
//                idx.toString(), Toast.LENGTH_SHORT
//            ).show()
            val contact = contacts[idx]
            val sms = Uri.parse("smsto:${contact.phone}")
            val intent = Intent(Intent.ACTION_SENDTO, sms)
            intent.putExtra("sms_body", "Привет, ${contact.name}!")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Не найден почтовый клиент", Toast.LENGTH_SHORT).show()
            }
        }

    }
}