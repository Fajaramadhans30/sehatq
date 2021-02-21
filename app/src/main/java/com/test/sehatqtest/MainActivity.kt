package com.test.sehatqtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skydoves.whatif.whatIfNotNull
//import com.test.sehatqtest.view.SearchActivity
import com.test.sehatqtest.view.fragment.CartFragment
import com.test.sehatqtest.view.fragment.FeedFragment
import com.test.sehatqtest.view.fragment.HomeFragment
import com.test.sehatqtest.view.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "onCreateeeee: ")

        toolbar = supportActionBar!!

        openFragment(HomeFragment())

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    toolbar.title = "Home"
                    val homeFragment = HomeFragment.newInstance()
                    openFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_feed -> {
                    toolbar.title = "Feed"
                    val feedFragment = FeedFragment.newInstance()
                    openFragment(feedFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_cart -> {
                    toolbar.title = "Cart"
                    val cartFragment = CartFragment.newInstance()
                    openFragment(cartFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    toolbar.title = "Profile"
                    val profileFragment = ProfileFragment.newInstance()
                    openFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.optionseleected,menu)
        return true
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            // User chose the "Print" item
            //Toast.makeText(this,"Print action",Toast.LENGTH_LONG).show()
//            val intent = Intent(this@MainActivity, SearchActivity::class.java);
//            startActivity(intent);
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}