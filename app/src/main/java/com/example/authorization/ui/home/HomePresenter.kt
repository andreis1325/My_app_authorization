package com.example.authorization.ui.home

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.net.repo.ArticleRepo
import com.example.authorization.net.repo.BlogRepo
import com.example.authorization.net.repo.ReportRepo
import com.example.authorization.ui.base.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.instance
import retrofit2.Retrofit

@InjectViewState
class HomePresenter() : BaseMvpPresenter<HomeView>() {

    private val blogRepo by MyApp.kodein.instance<BlogRepo>()
    private val articleRepo by MyApp.kodein.instance<ArticleRepo>()
    private val reportRepo by MyApp.kodein.instance<ReportRepo>()
    private val retrofit by MyApp.kodein.instance<Retrofit>()


    fun onCreate() {
        addDisposable(articleRepo.getArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            }
            ))

    }
}