package com.example.authorization.ui.fragments.profile

import android.net.Uri
import com.example.authorization.ui.base.BaseMvpView

interface ProfileView : BaseMvpView {
    fun switchToLoginActivity()
    fun setGoogleData(
        gEmail: String?,
        gName: String?,
        gId: String?,
        gImage: Uri?
    )
}