package com.betterride.bradmin.viewcontrollers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.betterride.bradmin.R
import com.betterride.bradmin.UserSession
import com.betterride.bradmin.network.BRApi

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {
    var check: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        NoAccountbutton.setOnClickListener {view->
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        butonIngresar.setOnClickListener { view ->
            var username = UsernameEditText.text.toString()
            var password = PasswordEditText.text.toString()
            //Log.d("BradminApp", error!!.message)
            if (!username.equals("") && !password.equals("")) {
                BRApi.requestPostSupervisorValidate(username,password,
                    { response -> check = response!!.message == "Ok"

                        Log.d("BradminApp", response!!.message)
                        if(check) {
                            UserSession.supervisor = response.data
                            startActivity(Intent(this, MainActivity::class.java))}else{
                        }
                    },
                    { error ->  Log.d("BradminApp", error!!.message) }
                )

            }
        }
    }
}