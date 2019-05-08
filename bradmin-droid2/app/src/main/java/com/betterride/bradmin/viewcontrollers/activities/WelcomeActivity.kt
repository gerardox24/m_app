package com.betterride.bradmin.viewcontrollers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.betterride.bradmin.R

import kotlinx.android.synthetic.main.content_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        startButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
