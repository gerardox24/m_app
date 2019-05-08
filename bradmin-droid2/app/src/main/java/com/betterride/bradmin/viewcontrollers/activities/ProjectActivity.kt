package com.betterride.bradmin.viewcontrollers.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.androidnetworking.error.ANError
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Project
import com.betterride.bradmin.models.Session
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.ResponseSession
import com.betterride.bradmin.viewcontrollers.adapters.SessionsAdapter

import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.content_project.*

class ProjectActivity : AppCompatActivity() {
    var sessions = ArrayList<Session>()
    lateinit var proj: Project
    lateinit var sessionsRecyclerView: RecyclerView
    lateinit var sessionsAdapter: SessionsAdapter
    lateinit var sessionsLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent ?: return
        proj = Project.from(intent.extras)
        projectNameTextView.text = proj.name
        var year = proj.date.subSequence(0,4).toString()
        var month = proj.date.subSequence(5,7).toString()
        var day = proj.date.subSequence(8,10).toString()
        dateProjectTextView.text = day + "/" + month +"/"+year

        sessionsRecyclerView = sessionRecyclerView
        sessionsAdapter = SessionsAdapter(sessions, this)
        sessionsLayoutManager = GridLayoutManager(this, 1)
        sessionsRecyclerView.adapter = sessionsAdapter
        sessionsRecyclerView.layoutManager = sessionsLayoutManager

        BRApi.requestGetSessions(proj.id,"1234",
            {response -> handleResponse(response)},
            {error -> handleError(error)}
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_project, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.editAction -> {
                startActivity(
                    Intent(applicationContext, EditProjectActivity::class.java).
                        putExtras(proj.toBundle()))
                return true
            }
            R.id.deleteAction -> {
                alertDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alertDialog(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Delete project?")
        dialogbuilder.setPositiveButton("DELETE",
            { dialogInterface: DialogInterface, i: Int ->
                BRApi.requestDeleteOrganization(proj.id,"1234",
                    {responsa-> startActivity(Intent(applicationContext, MainActivity::class.java))},
                    {error->  Log.d("BradminApp", error!!.message)})


            })
        dialogbuilder.setNegativeButton("CANCEL",
            { dialogInterface: DialogInterface, i: Int ->

            })
        val alertDialog = dialogbuilder.create()
        alertDialog.show()

    }


    private fun handleResponse(response: ResponseSession?){
        sessions = response!!.sessions!!
        Log.d("BradminApp", "Found ${sessions.size} junctions")
        sessionsAdapter.sessions = sessions
        sessionsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }
}
