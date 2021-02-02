package com.example.authorization.ui.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.core.net.toUri
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.model.*
import com.example.authorization.ui.base.BaseMvpPresenter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import org.kodein.di.instance

@InjectViewState
class ProfilePresenter : BaseMvpPresenter<ProfileView>() {

    private val authData by MyApp.kodein.instance<SharedPreferences>()

    fun onCreate() {
        setGoogleData()
    }

    private fun setGoogleData() {

        viewState.setGoogleData(
            authData.googleEmail,
            authData.googleName,
            authData.googleId,
            authData.googleImage?.toUri()
        )
    }

    fun exit() {
        authData.isLoggedIn = false
        viewState.switchToLoginActivity()
    }

}