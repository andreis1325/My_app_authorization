package com.example.authorization.ui.base

import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpPresenter

abstract class BaseMvpPresenter<V : BaseMvpView> : MvpPresenter<V>() {

    protected fun showMessage(@StringRes text: Int) {
        viewState.showMessage(text)
    }

    protected fun showMessage(text: String) {
        viewState.showMessage(text)
    }

}
