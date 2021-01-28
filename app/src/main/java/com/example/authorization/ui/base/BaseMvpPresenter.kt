package com.example.authorization.ui.base

import android.text.Editable
import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpPresenter
import com.example.authorization.MyApp
import com.example.authorization.net.responses.ArticleResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.instance
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseMvpPresenter<V : BaseMvpView> : MvpPresenter<V>() {
    private val compositeDisposable: CompositeDisposable by MyApp.kodein.instance()
    private var newsItems = ArrayList<ArticleResponse>()

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
