package com.betterride.bradmin.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Operator
import kotlinx.android.synthetic.main.item_operator.view.*

class OperatorsAdapter(var operators: ArrayList<Operator>, val context: Context) :
    RecyclerView.Adapter<OperatorsAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val position = operators.get(position)
        holder.updateFrom(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_operator, parent, false))
    }

    override fun getItemCount(): Int {
        return operators.size

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fullNameOperatorTextView = view.fullNameOperatorTextView
        val operatorImageView = view.operatorImageView

        fun updateFrom(operator: Operator) {
            fullNameOperatorTextView.text = "${operator.name} ${operator.last_name}"
            operatorImageView.setImageUrl(operator.photo)
        }
    }
}