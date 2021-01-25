package com.example.authorization.ui.fragments.home

import android.text.Editable
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
import java.util.*
import kotlin.collections.ArrayList

@InjectViewState
class HomePresenter : BaseMvpPresenter<HomeView>() {

    private val blogRepo by MyApp.kodein.instance<BlogRepo>()
    private val articleRepo by MyApp.kodein.instance<ArticleRepo>()
    private val reportRepo by MyApp.kodein.instance<ReportRepo>()
    private var newsItems = ArrayList<ArticleResponse>()
    private lateinit var itemName: MenuItem

    fun onCreate(itemClickObservable: Observable<ArticleResponse>) {
        setNews(getNews())
        initOnItemClickListener(itemClickObservable)
    }

    fun onSearchClicked() {
        viewState.changeImage()
        viewState.showForm()
    }

    fun onCrossClicked() {
        viewState.changeImage()
        viewState.showTitle()
    }

    fun findSearchedItems(text: String): List<ArticleResponse> {
        val searchText = text.toLowerCase(Locale.ROOT)
        val searchedItems = ArrayList<ArticleResponse>()

        for (item in newsItems) {
            if (item.title?.toLowerCase(Locale.ROOT)?.indexOf(searchText) != -1)
                searchedItems.add(item)
        }
        return searchedItems
    }

    // MARK: Assistant function
    private fun initOnItemClickListener(itemClickObservable: Observable<ArticleResponse>) {
        addDisposable(
            itemClickObservable.subscribe {
                viewState.goToArticleItem(it.id, itemName)
            }
        )
    }

    fun onTubSwitched(itemName: MenuItem ){
        setNews(getNews(itemName))
    }

    private fun getNews(itemName: MenuItem = MenuItem.Article): Observable<ArrayList<ArticleResponse>> {

        lateinit var articleResponse: Observable<ArrayList<ArticleResponse>>
        when (itemName) {
            is MenuItem.Article -> {
                articleResponse = articleRepo.getArticles()
                this.itemName = MenuItem.Article
            }
            is MenuItem.Blog -> {
                articleResponse = blogRepo.getBlogs()
                this.itemName = MenuItem.Blog
            }
            is MenuItem.Report -> {
                articleResponse = reportRepo.getReports()
                this.itemName = MenuItem.Report
            }
        }
        return articleResponse
    }

        private fun setNews(articleResponse: Observable<ArrayList<ArticleResponse>>){
            addDisposable(articleResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    newsItems.clear()
                    newsItems.addAll(it)
                    viewState.setNews(it)
                }, {

                }
                ))
        }
}