package com.betterride.bradmin.models

import android.os.Bundle

data class Supervisor(
    val id: String,
    val name: String,
    val last_name: String,
    val email: String,
    val username: String,
    val password: String,
    val organization_id: String,
    val role: String,
    val gender: String,
    val photo: String)
{
    companion object {
        fun from(bundle: Bundle): Supervisor {
            return Supervisor(
                bundle.getString("id")!!,
                bundle.getString("name")!!,
                bundle.getString("last_name")!!,
                bundle.getString("email")!!,
                bundle.getString("username")!!,
                bundle.getString("password")!!,
                bundle.getString("organization_id")!!,
                bundle.getString("role")!!,
                bundle.getString("gender")!!,
                bundle.getString("photo")!!
            )
        }
    }
    
    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("id",id)
        bundle.putString("name",name)
        bundle.putString("last_name",last_name)
        bundle.putString("email",email)
        bundle.putString("username",username)
        bundle.putString("password",password)
        bundle.putString("organization_id",organization_id)
        bundle.putString("role", role)
        bundle.putString("gender", gender)
        bundle.putString("photo",photo)
        return bundle
    }
}