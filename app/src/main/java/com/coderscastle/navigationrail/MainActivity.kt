package com.coderscastle.navigationrail

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {

    private lateinit var navigationRail: NavigationRailView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigationRail = findViewById(R.id.navigation_rail)
        clickLister()

        replaceFragment(HomeFragment())
    }

    private fun clickLister (){
        navigationRail.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.message -> replaceFragment(MessageFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
                R.id.alarm -> replaceFragment(AlarmFragment())
            }
            true
        }

        navigationRail.setOnItemReselectedListener {
            val reselectText= getString(R.string.reselected, it.title)
            Toast.makeText(this, reselectText, Toast.LENGTH_SHORT).show()

        }

        val floatingActionButton = navigationRail.headerView?.findViewById<FloatingActionButton>(R.id.fab)
        floatingActionButton?.setOnClickListener {
            val fabText= getString(R.string.reselected, it.contentDescription)
            Toast.makeText(this, fabText, Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
       val fm =supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}