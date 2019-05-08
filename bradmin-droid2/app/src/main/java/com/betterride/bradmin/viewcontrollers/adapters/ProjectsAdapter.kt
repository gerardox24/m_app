package com.betterride.bradmin.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betterride.bradmin.R
import com.betterride.bradmin.models.Project
import com.betterride.bradmin.viewcontrollers.activities.ProjectActivity
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectsAdapter(var projects: ArrayList<Project>, val context: Context
                    ): RecyclerView.Adapter<ProjectsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_project,parent, false)
        )
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects.get(position)
        holder.updateFrom(project)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleProjectsTextView = view.titleProjectTextView
        val dayProjectTextView = view.dateProjectTextView
        val projectLayout = view.item_project

        fun updateFrom(project: Project){
            titleProjectsTextView.text = project.name
            var year = project.date.subSequence(0,4).toString()
            var month = project.date.subSequence(5,7).toString()
            var day = project.date.subSequence(8,10).toString()
            dayProjectTextView.text = day + "/" + month +"/"+year
            projectLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(
                    Intent(context, ProjectActivity::class.java)
                        .putExtras(project.toBundle()))
            }
        }
    }
}