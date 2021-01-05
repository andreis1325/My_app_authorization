package com.example.authorization

import android.app.Application
import io.realm.Realm

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm(){
        Realm.init(this@MyApp)
    }
}