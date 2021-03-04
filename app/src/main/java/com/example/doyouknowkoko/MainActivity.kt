package com.example.doyouknowkoko

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val elementList = generateDummyList(500)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_xml)
        recyclerView.adapter = RecyclerAdapter(elementList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<Element> {
        val list = ArrayList<Element>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_explore
                else -> R.drawable.ic_fastfood
            }
            val item = Element(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}