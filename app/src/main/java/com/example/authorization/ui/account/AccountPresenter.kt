package com.example.authorization.ui.account

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.R
import com.example.authorization.model.isLoggedIn
import com.example.authorization.model.userEmail
import com.example.authorization.model.userPassword
import com.example.authorization.repo.UserRepo
import com.example.authorization.ui.base.BaseMvpPresenter
import com.example.authorization.utils.extensions.isEmailValid
import org.kodein.di.instance

@InjectViewState
class AccountPresenter() : BaseMvpPresenter<AccountView>() {

    private val sharedPreference by MyApp.kodein.instance<SharedPreferences>()

    fun logOut(){
        sharedPreference.isLoggedIn = false
    }
}