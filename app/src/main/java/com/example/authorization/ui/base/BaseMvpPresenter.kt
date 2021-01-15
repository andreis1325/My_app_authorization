package com.example.authorization.ui.base

import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpPresenter
import com.example.authorization.MyApp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.kodein.di.instance

abstract class BaseMvpPresenter<V : BaseMvpView> : MvpPresenter<V>() {
    private val compositeDisposable: CompositeDisposable by MyApp.kodein.instance()

    protected fun showMessage(@StringRes text: Int) {
        viewState.showMessage(text)
    }

    protected fun showMessage(text: String) {
        viewState.showMessage(text)
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun removeDisposable(disposable: Disposable) {
        compositeDisposable.remove(disposable)
    }


}
