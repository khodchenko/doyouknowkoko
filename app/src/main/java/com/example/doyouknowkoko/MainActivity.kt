package com.example.doyouknowkoko

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doyouknowkoko.R.id.nav_home
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var recyclerView: RecyclerView
    private var adapter: PersonAdapter? = null // Create Object of the Adapter class
    private var mbase: DatabaseReference? = null // Create object of the Firebase Realtime Database
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
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


        //                       HOOKS
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        //toolbar = findViewById(R.id.toolbar)
        //                        TOOLBAR

        //todo setSupportActionBar(toolbar)

        //                      Navigation Drawer Menu
        navigationView.bringToFront()
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
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
        when (item.itemId) {
            R.id.nav_home2 -> startActivity(Intent(this@MainActivity,AddOutfit::class.java))
//                    case R.id.nav_home2: Intent intent = new Intent(
//                MainActivity.this,
//                AddOutfit.class);
//            startActivity(intent);
//                break;
//            case R . id . nav_share :
//                    Toast toast = new Toast (this, "Share", Toast.LENGTH_SHORT )
//                .show();
//            break;
        }

        return true
    }


}