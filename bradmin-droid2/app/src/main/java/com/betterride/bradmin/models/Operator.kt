package com.betterride.bradmin.models

import android.os.Bundle

data class Operator(val name: String,
                    val last_name: String,
                    val email: String,
                    val gender: String,
                    val photo: String
)
{
    companion object {
        fun from(bundle: Bundle): Operator {
            return Operator(
                bundle.getString("name")!!,
                bundle.getString("last_name")!!,
                bundle.getString("email")!!,
                bundle.getString("gender")!!,
                bundle.getString("photo")!!

            )
        }
    }

    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("last_name", last_name)
        bundle.putString("email", email)
        bundle.putString("gender", gender)
        bundle.putString("photo", photo)
        return bundle
    }
}