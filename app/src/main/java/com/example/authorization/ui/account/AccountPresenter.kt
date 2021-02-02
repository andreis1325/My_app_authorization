package com.example.authorization.ui.account

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.ui.base.BaseMvpPresenter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@InjectViewState
class AccountPresenter : BaseMvpPresenter<AccountView>()
