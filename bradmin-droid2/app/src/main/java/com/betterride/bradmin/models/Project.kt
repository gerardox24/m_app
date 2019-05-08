package com.betterride.bradmin.models

import android.os.Bundle

data class Project(
    val id: String = "",
    val name: String,
    val date: String,
    val supervisor_id: String) {
    companion object {
        fun from(bundle: Bundle): Project {
            return Project(
                bundle.getString("id")!!,
                bundle.getString("name")!!,
                bundle.getString("date")!!,
                bundle.getString("supervisor_id")!!
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("name", name)
        bundle.putString("date", date)
        bundle.putString("supervisor_id", supervisor_id)
        return bundle
    }
}