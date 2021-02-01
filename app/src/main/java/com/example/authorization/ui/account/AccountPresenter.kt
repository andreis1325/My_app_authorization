package com.example.authorization.ui.account

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.ui.base.BaseMvpPresenter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@InjectViewState
class AccountPresenter : BaseMvpPresenter<AccountView>() {

    lateinit var mGoogleSignInClient: GoogleSignInClient

    fun signOutGoogle(context: AccountActivity) {

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(context) {

            }
    }
}