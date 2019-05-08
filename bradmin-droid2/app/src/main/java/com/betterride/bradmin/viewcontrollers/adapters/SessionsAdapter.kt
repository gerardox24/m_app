package com.betterride.bradmin.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Session
import com.betterride.bradmin.viewcontrollers.activities.SessionActivity
import kotlinx.android.synthetic.main.item_session.view.*

class SessionsAdapter(var sessions: ArrayList<Session>,
                      val context: Context
) : RecyclerView.Adapter<SessionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_session, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sessions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val junction = sessions.get(position)
        holder.updateFrom(junction)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstAvenueTextView = view.firstAvenueTextView
        val secondAvenueTextView = view.secondAvenueTextView

        val dateTextView = view.dateTexView
        val sessionLayout = view.item_session

        fun updateFrom(session: Session){
            firstAvenueTextView.text = session.avenue_first
            secondAvenueTextView.text = session.avenue_second
            var year = session.date.subSequence(0,4).toString()
            var month = session.date.subSequence(5,7).toString()
            var day = session.date.subSequence(8,10).toString()
            dateTextView.text = day + "/" + month +"/"+year
            sessionLayout.setOnClickListener { view->
                val context = view.context
                context.startActivity(
                    Intent(context, SessionActivity::class.java)
                        .putExtras(session.toBundle()))
            }
        }

    }
}