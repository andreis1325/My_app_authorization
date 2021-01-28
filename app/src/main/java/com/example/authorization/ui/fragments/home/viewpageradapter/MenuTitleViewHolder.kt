package com.example.authorization.ui.fragments.home.viewpageradapter

import android.view.View
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseViewHolder
import com.example.authorization.ui.fragments.home.articleadapter.ArticleAdapter
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.first_item_viewpager.view.*
import java.util.*
import kotlin.collections.ArrayList

class MenuTitleViewHolder(
    itemView: View,
    var itemClickSubject: PublishSubject<ArticleResponse>,
) : BaseViewHolder<ArrayList<ArticleResponse>>(itemView) {

    lateinit var articleAdapter: ArticleAdapter

    override fun bind(model: ArrayList<ArticleResponse>) {
        initAdapter(model)
    }

    private fun initAdapter(model: ArrayList<ArticleResponse>) {
        articleAdapter = ArticleAdapter(model, itemClickSubject)
        itemView.vRvArticles.adapter = articleAdapter
    }
}