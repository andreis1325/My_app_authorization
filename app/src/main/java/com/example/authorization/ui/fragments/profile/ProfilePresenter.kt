package com.example.authorization.ui.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.model.isLoggedIn
import com.example.authorization.ui.base.BaseMvpPresenter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import org.kodein.di.instance

@InjectViewState
class ProfilePresenter : BaseMvpPresenter<ProfileView>() {

    private val authData by MyApp.kodein.instance<SharedPreferences>()

    fun onCreate(context: Context?) {
        val acct = GoogleSignIn.getLastSignedInAccount(context)

        var gEmail = acct?.email
        var gName = acct?.displayName
        var gId = acct?.id
        var gImage = acct?.photoUrl

        viewState.setGoogleData(gEmail,gName,gId, gImage )
    }

    fun exit(){
        authData.isLoggedIn = false
        viewState.switchToLoginActivity()
    }

}