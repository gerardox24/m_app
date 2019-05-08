package com.betterride.bradmin.viewcontrollers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.betterride.bradmin.R
import com.betterride.bradmin.UserSession
import com.betterride.bradmin.models.Organization
import com.betterride.bradmin.network.BRApi

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity() {
    var check: Boolean = false;
    lateinit var organizacion: Organization;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        butonRegistrar.setOnClickListener { view ->
            var name = NameText.text.toString()
            var last_name = LastNameText.text.toString()
            var email = EmailText.text.toString()
            var username = UsernameText.text.toString()
            var password = PasswordText.text.toString()
            var genero = ""

            var token: String = TokenText.text.toString()
            Log.d("BradminApp", name + last_name+email+username+password+genero+token)
            if (RButtonMale.isChecked()){
                genero = "Male"
            }
            else genero = "Female"
            Log.d("BradminApp", name + last_name+email+username+password+genero+token)

            if (!name.equals("") && !last_name.equals("") && !email.equals("")
                && !username.equals("") && !password.equals("") && !genero.equals("") && !token.equals("")
            ) {
                Log.d("BradminApp", name)

                BRApi.requestPostOrganizationData(token, { response ->
                    check = response!!.message == "Ok"
                    if(check) organizacion = response.data!!
                    Log.d("BradminApp", check.toString()+"first")
                    Log.d("BradminApp", response!!.message)
                    BRApi.requestPostSupervisor(name,last_name,email,username,password,organizacion.id,"sup",genero,token,
                        { response->
                            BRApi.requestPostSupervisorValidate(username,password,
                                { response -> check = response!!.message == "Ok"

                                    Log.d("BradminApp", response!!.message)
                                    if(check) {
                                        UserSession.supervisor=response.data

                                        startActivity(Intent(this, MainActivity::class.java))}else{
                                    }
                                },
                                { error ->  Log.d("BradminApp", error!!.message) }
                            )
                        },
                        { error->       Log.d("BradminApp", error!!.message)})
                        }, { error ->  Log.d("BradminApp", error!!.message)

                    })
            } else{
                Log.d("BradminApp", "Completa")
            }
        }

    }

}