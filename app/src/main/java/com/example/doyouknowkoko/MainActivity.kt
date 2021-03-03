package com.example.doyouknowkoko

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), MyRecyclerViewAdapter.ItemClickListener {

    var adapter: MyRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        // data to populate the RecyclerView with
        val animalNames: ArrayList<String> = ArrayList()
        animalNames.add("Horse")
        animalNames.add("Cow")
        animalNames.add("Camel")
        animalNames.add("Sheep")
        animalNames.add("Goat")

        // set up the RecyclerView
        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_xml)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter(this, animalNames)
        adapter?.setClickListener(this)
        recyclerView.adapter = adapter
    }
    override fun onItemClick(view: View?, position: Int) {
        Toast.makeText(this, "You clicked " + adapter?.getItem(position).toString() + " on row number " + position, Toast.LENGTH_SHORT).show()
    }
}