package com.example.authorization.ui.home

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.net.repo.ArticleRepo
import com.example.authorization.net.repo.BlogRepo
import com.example.authorization.net.repo.ReportRepo
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseMvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.instance

@InjectViewState
class HomePresenter() : BaseMvpPresenter<HomeView>() {

    private val blogRepo by MyApp.kodein.instance<BlogRepo>()
    private val articleRepo by MyApp.kodein.instance<ArticleRepo>()
    private val reportRepo by MyApp.kodein.instance<ReportRepo>()

    fun onCreate(itemClickObservable: Observable<ArticleResponse>) {
        setNews()
        initOnItemClickListener(itemClickObservable)
    }

    fun onSearchClicked(){
        viewState.changeImage()
        viewState.showForm()
    }

    fun onCrossClicked(){
        viewState.changeImage()
        viewState.showTitle()
    }

    private fun initOnItemClickListener(itemClickObservable: Observable<ArticleResponse>) {
        addDisposable(
            itemClickObservable.subscribe({
                viewState.goToArticleItem(it)
            }, {

            })
        )
    }

    private fun setNews() {
        addDisposable(articleRepo.getArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setNews(it)
            }, {

            }
            ))

    }
}