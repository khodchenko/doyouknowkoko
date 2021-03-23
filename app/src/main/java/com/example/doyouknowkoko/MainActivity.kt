package com.example.doyouknowkoko


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recyclerView: RecyclerView
    private var adapter: RecyclerAdapter? = null // Create Object of the Adapter class
    private var mbase: DatabaseReference? = null // Create object of the Firebase Realtime Database
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle //slide menu
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        // Create a instance of the database and get its reference
        mbase = FirebaseDatabase.getInstance().reference
        recyclerView = findViewById(R.id.recycler1)
        // To display the Recycler view linearly
        recyclerView.layoutManager = LinearLayoutManager(this)
        // It is a class provide by the FirebaseUI to make a query in the database to fetch appropriate data
        val options = FirebaseRecyclerOptions.Builder<Outfit>()
            .setQuery(mbase!!, Outfit::class.java)
            .build()
        // Connecting object of required Adapter class to the Adapter class itself
        adapter = RecyclerAdapter(options)

        // Connecting Adapter class with the Recycler view*/
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener(this)

        //                       HOOKS
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        //toolbar = findViewById(R.id.toolbar)
        //------------------------TOOLBAR

        //todo setSupportActionBar(toolbar)

        //-------------------Navigation Drawer Menu--------------

        //Hide or show icons
        val menu: Menu = navigationView.menu
        menu.findItem(R.id.nav_logout).isVisible = false
        menu.findItem(R.id.nav_profile).isVisible = false

        navigationView.bringToFront()
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        navigationView.setCheckedItem(R.id.nav_home) //set nav_home as default in drawer
    }

    //swipe refresh layout
    override fun onRefresh() {
        adapter?.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
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
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    //to avoid closing the application on Back pressed
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //to save selected status on items on list
    // logic on items in drawer menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //todo
        val itemCounter = adapter?.itemCount.toString()
        val intent = Intent(this, AddOutfit::class.java).apply {
            Log.d(TAG, "onNavigationItemSelected: $itemCounter")
            putExtra("ic", itemCounter)
        }
        when (item.itemId) {
            R.id.nav_home -> startActivity(Intent(this@MainActivity, MainActivity::class.java))
            R.id.nav_addOutfit -> startActivity(intent)
            R.id.nav_home3 -> startActivity(Intent(this@MainActivity, Test::class.java))
            R.id.nav_share -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
//
        }
        drawerLayout.closeDrawer(GravityCompat.START)//when any menu would be selected it will close
        // if off and perform that indicate each time
        return true
    }


}