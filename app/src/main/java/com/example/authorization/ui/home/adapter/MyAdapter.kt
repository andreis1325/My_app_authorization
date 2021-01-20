package com.example.authorization.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseListAdapter
import com.example.authorization.ui.base.BaseViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class MyAdapter() : BaseListAdapter<ArticleResponse>() {
    private val itemClickSubject = PublishSubject.create<ArticleResponse>()
    val itemClickObservable: Observable<ArticleResponse> = itemClickSubject

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticleResponse> {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false),
            itemClickSubject
        )
    }
}


