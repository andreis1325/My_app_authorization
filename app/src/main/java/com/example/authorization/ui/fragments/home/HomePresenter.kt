package com.example.authorization.ui.fragments.home

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.net.repo.ArticleRepo
import com.example.authorization.net.repo.BlogRepo
import com.example.authorization.net.repo.ReportRepo
import com.example.authorization.net.responses.ArticleItemsWithBlogAndReport
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseMvpPresenter
import com.example.authorization.utils.transformations.MenuItem
import com.example.authorization.utils.transformations.MenuItem.Article
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import org.kodein.di.instance
import java.util.*
import kotlin.collections.ArrayList

@InjectViewState
class HomePresenter : BaseMvpPresenter<HomeView>() {

    private val blogRepo by MyApp.kodein.instance<BlogRepo>()
    private val articleRepo by MyApp.kodein.instance<ArticleRepo>()
    private val reportRepo by MyApp.kodein.instance<ReportRepo>()

    private lateinit var newsItemsList: ArrayList<ArrayList<ArticleResponse>>
    private var currentItemName: MenuItem = Article
    private var currentShowingPosition: Int = 0

    fun onCreate(itemClickObservable: Observable<ArticleResponse>) {
        initOnItemClickListener(itemClickObservable)
        loadDataAndUpdateUI()
        setTitleNamesAndLinkViewPagerAndTabLayout()

    }

    private fun initOnItemClickListener(itemClickObservable: Observable<ArticleResponse>) {
        addDisposable(
            itemClickObservable.subscribe {
                viewState.goToArticleItem(it.id, currentItemName)
            }
        )
    }

    private fun loadDataAndUpdateUI() {
        val articleItems = articleRepo.getArticles()
        val blogItems = blogRepo.getBlogs()
        val reportItems = reportRepo.getReports()

        viewState.hideRefreshAnimation()
        addDisposable(
            Observable.zip(
                articleItems, blogItems, reportItems,
                Function3<ArrayList<ArticleResponse>, ArrayList<ArticleResponse>, ArrayList<ArticleResponse>, ArticleItemsWithBlogAndReport> { articleItems, blogItems, reportItems ->
                    ArticleItemsWithBlogAndReport(articleItems, blogItems, reportItems)
                }
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    newsItemsList = arrayListOf(it.articleItems, it.blogItems, it.reportItems)
                    viewState.updateArticles(newsItemsList)
                }, {
                })
        )
    }

    private fun setTitleNamesAndLinkViewPagerAndTabLayout(){
        viewState.setTitleNames()
        viewState.linkViewPagerAndTabLayout()
    }

    fun onPageChanged(position: Int) {
        currentShowingPosition = position

        when (position) {
            0 -> currentItemName = Article
            1 -> currentItemName = MenuItem.Blog
            2 -> currentItemName = MenuItem.Report
        }
    }

    fun onSearchClicked() {
        viewState.changeImage()
        viewState.showForm()
    }

    fun onCrossClicked() {
        viewState.changeImage()
        viewState.showTitle()
    }

    fun onTextChanged(text: CharSequence?) {

        val searchText = text.toString()
        val searchedItems: ArrayList<ArrayList<ArticleResponse>> = arrayListOf()

        for (index in 0 until newsItemsList.size) {
            if (index != currentShowingPosition)
                searchedItems.add(newsItemsList[index])
            else
                searchedItems.add(findSearchedItems(searchText))
        }
        viewState.updateArticles(searchedItems)
    }

    private fun findSearchedItems(searchText: String): ArrayList<ArticleResponse> {
        val searchedNewsItems: ArrayList<ArticleResponse> = arrayListOf()

        newsItemsList[currentShowingPosition].forEach { item ->
            if (item.title?.contains(searchText, true) == true)
                searchedNewsItems.add(item)
        }

        return searchedNewsItems
    }

    fun onRefreshNews() {
        viewState.showRefreshAnimation()
        loadDataAndUpdateUI()

    }
}