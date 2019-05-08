package pe.edu.upc.iceandfire

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.jacksonandroidnetworking.JacksonParserFactory
import com.orm.SugarApp

class IceandfireApp : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.setParserFactory(JacksonParserFactory())
    }
}