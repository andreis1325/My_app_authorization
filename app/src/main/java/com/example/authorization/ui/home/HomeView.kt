package com.example.authorization.ui.home

import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseMvpView


interface HomeView : BaseMvpView {
    fun changeImage()
    fun showForm()
    fun showTitle()
    fun setNews(it: ArrayList<ArticleResponse>)
    fun goToArticleItem(it: ArticleResponse)
}