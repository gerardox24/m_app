package com.betterride.bradmin.viewcontrollers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Operator

import kotlinx.android.synthetic.main.activity_operator.*
import kotlinx.android.synthetic.main.content_operator.*

class OperatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent ?: return
        val operator = Operator.from(intent.extras)
        pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher)
        pictureImageView.setErrorImageResId(R.mipmap.ic_launcher)
        pictureImageView.setImageUrl(operator.photo)
        nameText.text = operator.name
        lastnameText.text = operator.last_name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_operator, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
