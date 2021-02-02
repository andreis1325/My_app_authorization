package com.example.authorization.ui.fragments.extendednews

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.net.repo.ArticleRepo
import com.example.authorization.net.repo.BlogRepo
import com.example.authorization.net.repo.ReportRepo
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseMvpPresenter
import com.example.authorization.utils.transformations.MenuItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.instance

@InjectViewState
class ExtendedNewsPresenter : BaseMvpPresenter<ExtendedNewsView>() {

    private val articleRepo by MyApp.kodein.instance<ArticleRepo>()
    private val blogRepo by MyApp.kodein.instance<BlogRepo>()
    private val reportRepo by MyApp.kodein.instance<ReportRepo>()

    fun onCreate(newsId: String?, itemName:MenuItem) {
        if (newsId != null) {
            setNews(newsId, itemName)
        }
    }

    private fun setNews(newsId: String, itemName: MenuItem = MenuItem.Article) {
        val newsItem: Observable<ArticleResponse> = when (itemName) {
            is MenuItem.Article -> articleRepo.getArticleById(newsId)
            is MenuItem.Blog -> blogRepo.getBlogById(newsId)
            is MenuItem.Report-> reportRepo.getReportById(newsId)
        }

        addDisposable(newsItem
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setNews(it)
            }, {

            }
            ))
    }
}