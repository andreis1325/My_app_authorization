package com.example.authorization.ui.fragments.home.articleadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseListAdapter
import com.example.authorization.ui.base.BaseViewHolder
import io.reactivex.subjects.PublishSubject

class ArticleAdapter(
    elements: MutableList<ArticleResponse> = mutableListOf(),
    var itemClickSubject: PublishSubject<ArticleResponse>
) :
    BaseListAdapter<ArticleResponse>(elements) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticleResponse> {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false),
            itemClickSubject
        )
    }
}


