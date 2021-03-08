package com.example.doyouknowkoko

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*
//TODO DELETE
class ReadActivity : AppCompatActivity() {
    private var listViewReadData: ListView? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var listData: MutableList<String>? = null
    private var dataBase: DatabaseReference? = null
    private val USER_KEY = "Outfit"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_layout)
        listViewReadData = findViewById(R.id.listView_ReadData)
        listData = ArrayList()
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData as ArrayList<String>)
        listViewReadData?.setAdapter(arrayAdapter)
        dataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        dataFromDataBase
    }

    private val dataFromDataBase: Unit
        private get() {
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (listData!!.size > 0) listData?.clear()
                    for (dataSnapshot in snapshot.children) {
                        val outfit = dataSnapshot.getValue(Outfit::class.java)!!
                        listData!!.add(outfit.name)
                    }
                    arrayAdapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {}
            }
            dataBase!!.addValueEventListener(valueEventListener)
        }
}