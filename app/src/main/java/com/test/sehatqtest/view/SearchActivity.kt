//package com.test.sehatqtest.view
//
//import android.content.Intent
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import com.skydoves.whatif.whatIfNotNull
//import com.test.sehatqtest.MainActivity
//import com.test.sehatqtest.R
//import com.test.sehatqtest.view.fragment.SearchFragment
//
//class SearchActivity : AppCompatActivity() {
//    private val TAG: String = "Finder Activity";
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search)
//        //setting toolbar
//        setSupportActionBar(findViewById(R.id.toolbar))
//        //home navigation
//        supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        supportActionBar?.setDisplayUseLogoEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//
//        //get parameter from mainactivity
//        searchFinder.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(s: Editable) {}
//
//            override fun beforeTextChanged(
//                s: CharSequence, start: Int,
//                count: Int, after: Int
//            ) {
//            }
//
//            override fun onTextChanged(
//                s: CharSequence, start: Int,
//                before: Int, count: Int
//            ) {
//                Log.d(TAG, "ketikan: " + s)
//
//            }
//        })
//
//        searchFinder.setOnFocusChangeListener(View.OnFocusChangeListener { view, hasFocus ->
//            if (hasFocus) {
//
//                val visibleFragment = getCurrentFragment()
//                visibleFragment.whatIfNotNull {
//                    Log.d(TAG, "ketikan: " + view.toString())
//                    if (it.javaClass.canonicalName == "com.test.sehatqtest.view.fragment.SearchFragment") {
//
//                        val fragobj =
//                            SearchFragment()
//                        loadFragment(fragobj)
//                    }
//                }
//            } else {
//                Toast.makeText(
//                    applicationContext,
//                    "Lost the focus",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        })
//
//
//        val fragobj =
//            SearchFragment()
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragobj)
//                .commitNow()
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        EventBus.getDefault().register(this)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        EventBus.getDefault().unregister(this)
//    }
//
//    fun getCurrentFragment(): Fragment? {
//        return supportFragmentManager
//            .findFragmentById(R.id.container)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//
//        return true
//    }
//
//    override fun onBackPressed() {
//        val count = supportFragmentManager.backStackEntryCount
//        if (count == 0) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//            //additional code
//        } else {
//            searchFinder.setText("")
//            searchFinder.clearFocus()
//            supportFragmentManager.popBackStack()
//        }
//    }
//
//
//    private fun loadFragment(fragment: Fragment) {
//        // load fragment
//
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
//}