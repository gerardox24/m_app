package com.betterride.bradmin.viewcontrollers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.betterride.bradmin.R

import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
