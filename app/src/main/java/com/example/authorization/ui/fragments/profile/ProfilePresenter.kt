package com.example.authorization.ui.fragments.profile

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.model.isLoggedIn
import com.example.authorization.ui.base.BaseMvpPresenter
import org.kodein.di.instance

@InjectViewState
class ProfilePresenter : BaseMvpPresenter<ProfileView>() {

    private val sharedPreference by MyApp.kodein.instance<SharedPreferences>()

    fun exit(){
        sharedPreference.isLoggedIn = false
        viewState.closeActivity()
    }

}