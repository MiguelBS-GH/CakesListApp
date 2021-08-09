package com.bram3r.cakeslistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bram3r.cakeslistapp.cakesList.CakesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainerFragment, CakesListFragment.newInstance())
                .commitNow()
        }
    }
}