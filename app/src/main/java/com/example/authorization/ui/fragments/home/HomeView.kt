package com.example.authorization.ui.fragments.home

import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseMvpView
import com.example.authorization.utils.transformations.MenuItem

interface HomeView : BaseMvpView {
    fun changeImage()
    fun showForm()
    fun showTitle()
    fun goToArticleItem(it: String?, itemName: MenuItem)
    fun updateArticles(newsItemsList: List<ArrayList<ArticleResponse>>)
    fun linkViewPagerAndTabLayout()
    fun setTitleNames()
}