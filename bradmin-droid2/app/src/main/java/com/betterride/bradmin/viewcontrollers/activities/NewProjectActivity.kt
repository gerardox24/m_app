package com.betterride.bradmin.viewcontrollers.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.betterride.bradmin.R
import com.betterride.bradmin.UserSession
import com.betterride.bradmin.network.BRApi

import kotlinx.android.synthetic.main.activity_new_project.*
import kotlinx.android.synthetic.main.content_new_project.*
import java.util.*

class NewProjectActivity : AppCompatActivity() {
    var yearInput: Int =0
    var monthInput: Int =0
    var dayInput: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_project)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTitle("New project")

        //var dateProjEditText = TextInputEditText(applicationContext)

        var calendar = Calendar.getInstance()

        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        dateImageButton.setOnClickListener { view ->
            var datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, yearT, monthT, dayOfMonthT ->
                yearInput = yearT
                monthInput = monthT
                dayInput = dayOfMonthT
                dateProjEditText.setText("$dayInput/$monthInput/$yearInput")
            }, year, month,day)
            datePicker.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.saveAction -> {
                if(validateInput()){
                    var date = Date(yearInput,monthInput,dayInput)
                    BRApi.requestPostAddProject(nameProjEditText.text.toString(),date, UserSession.supervisor!!.id,{
                        response -> Log.d("BradminApp", response!!.message)
                        Toast.makeText(applicationContext, "It was saved correctly", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    },{
                        error-> Log.d("BradminApp", error!!.message)
                        Toast.makeText(applicationContext, "It was not saved correctly", Toast.LENGTH_SHORT).show()
                    })

                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateInput(): Boolean {
        var resp = true
        if(!validateEditText(nameProjEditText, nameProjTextInput, getString(R.string.error_name_proj))){
            resp= false
        }
        if(!validateEditText(dateProjEditText, dateProjTextInput, getString(R.string.error_proj_date))){
            resp=false
        }
        return resp
    }

    private fun validateEditText(editText: TextInputEditText,
                                 textInputLayout: TextInputLayout,
                                 errorString: String): Boolean{
        if(editText.text.toString().trim().isEmpty()){
            textInputLayout.error = errorString
            return false
        }else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }
}
