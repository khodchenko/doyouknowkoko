package com.example.doyouknowkoko

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    var adapter // Create Object of the Adapter class
            : PersonAdapter? = null
    var mbase // Create object of the Firebase Realtime Database
            : DatabaseReference? = null

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle //slide menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().reference
        recyclerView = findViewById(R.id.recycler1)

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        val options = FirebaseRecyclerOptions.Builder<Person>()
            .setQuery(mbase!!, Person::class.java)
            .build()
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = PersonAdapter(options)
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter)

        //toggle
        var drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}