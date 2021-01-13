package com.example.authorization.ui.account

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.model.isLoggedIn
import com.example.authorization.ui.base.BaseMvpPresenter
import org.kodein.di.instance

@InjectViewState
class AccountPresenter() : BaseMvpPresenter<AccountView>() {

    private val sharedPreference by MyApp.kodein.instance<SharedPreferences>()

    fun logOut(){
        sharedPreference.isLoggedIn = false
        viewState.closeActivity()
    }
}