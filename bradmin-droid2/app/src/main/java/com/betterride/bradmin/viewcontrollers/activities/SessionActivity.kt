package com.betterride.bradmin.viewcontrollers.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.androidnetworking.error.ANError
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Operator
import com.betterride.bradmin.models.Session
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.ResponseOperator
import com.betterride.bradmin.viewcontrollers.adapters.OperatorsAdapter
import kotlinx.android.synthetic.main.activity_session.*
import kotlinx.android.synthetic.main.content_session.*


class SessionActivity : AppCompatActivity() {
    var operators = ArrayList<Operator>()
    lateinit var session: Session
    lateinit var operatorsRecyclerView: RecyclerView
    lateinit var operatorsAdapter: OperatorsAdapter
    lateinit var operatorsLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent ?: return
        session = Session.from(intent.extras)
        var year = session.date.subSequence(0, 4).toString()
        var month = session.date.subSequence(5, 7).toString()
        var day = session.date.subSequence(8, 10).toString()
        sessionDateTextView.text = "$day/$month/$year"
        sessionStartAtTextView.text = session.started_at
        sessionFinishAtTextView.text = session.finished_at
        sessionStatusTextView.text = if (session.status.equals("rea")) {
            "finished"
        } else "unfinished"
        sessionAvenueFirstTextView.text = session.avenue_first
        sessionAvenueSecondTextView.text = session.avenue_second

        operatorsRecyclerView = operatorRecyclerView
        operatorsAdapter = OperatorsAdapter(operators, this)
        operatorsLayoutManager = GridLayoutManager(this,1)
        operatorsRecyclerView.adapter = operatorsAdapter
        operatorsRecyclerView.layoutManager = operatorsLayoutManager

        BRApi.requestGetOperatorsbySession(session.id,
            {response -> handleResponse(response)},
            {error -> handleError(error)}
        )
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_analytics, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.analyticsAction -> {
                startActivity(
                    Intent(applicationContext, AnalyticsActivity::class.java).
                        putExtras(session.toBundle()))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleResponse(response: ResponseOperator?){
        operators = response!!.operators!!
        Log.d("BradminApp", "Found ${operators.size} junctions")
        operatorsAdapter.operators = operators
        operatorsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }
}
