package com.example.authorization.ui.fragments.extendednews

import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseMvpView

interface ExtendedNewsView : BaseMvpView {
    fun setNews(it: ArticleResponse)
}