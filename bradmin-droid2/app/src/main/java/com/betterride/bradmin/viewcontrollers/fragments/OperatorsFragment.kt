package com.betterride.bradmin.viewcontrollers.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError

import com.betterride.bradmin.R
import com.betterride.bradmin.UserSession
import com.betterride.bradmin.models.Operator
import com.betterride.bradmin.network.BRApi
import com.betterride.bradmin.network.ResponseOperator
import com.betterride.bradmin.viewcontrollers.activities.NewOperatorActivity
import com.betterride.bradmin.viewcontrollers.adapters.OperatorsAdapter
import kotlinx.android.synthetic.main.fragment_operators.view.*

class OperatorsFragment : Fragment() {
    var operators = ArrayList<Operator>()
    lateinit var operatorsRecyclerView: RecyclerView
    lateinit var operatorsAdapter: OperatorsAdapter
    lateinit var operatorsLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_operators, container, false)

        operatorsRecyclerView = view.operatorsRecyclerView
        operatorsAdapter = OperatorsAdapter(operators,view.context)
        operatorsLayoutManager = GridLayoutManager(view.context,1)
        operatorsRecyclerView.adapter = operatorsAdapter
        operatorsRecyclerView.layoutManager = operatorsLayoutManager

        view.addOpeFloatingAction.setOnClickListener { view ->
            startActivity(Intent(view.context, NewOperatorActivity::class.java))
        }

        BRApi.requestGetOperatorsbyOrganization(
            UserSession.supervisor!!.organization_id,
            { response -> handleResponse(response)},
            { error -> handleError(error)})

        return view
    }
    private fun handleResponse(response: ResponseOperator?){
        operators = response!!.operators!!
        Log.d("BradminApp", "Found ${operators.size} projects")
        operatorsAdapter.operators = operators
        operatorsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?){
        Log.d("BradminApp", anError!!.message)
    }


}
