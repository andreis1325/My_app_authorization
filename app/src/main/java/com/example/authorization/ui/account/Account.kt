package com.example.authorization.ui.account

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.authorization.MyApp
import com.example.authorization.R
import com.example.authorization.model.isLoggedIn
import com.example.authorization.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_account.*
import org.kodein.di.instance

class Account : AppCompatActivity() {

    private val sharedPreference by MyApp.kodein.instance<SharedPreferences>()
    lateinit var loginPresenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        initVar()
        initOnClickListeners()
    }

    private fun initVar(){
        loginPresenter = LoginPresenter()
        loginPresenter.onCreate()
    }

    private fun initOnClickListeners(){
        vBLogOut.setOnClickListener(){
            logOut()
        }
    }

    private fun logOut(){
        sharedPreference.isLoggedIn = false
        finish()
    }
}