package com.example.authorization.ui.home.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.bumptech.glide.Glide
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseViewHolder
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class ViewHolder(
    view: View,
    private val itemClickSubject: PublishSubject<ArticleResponse>
) : BaseViewHolder<ArticleResponse>(view) {

    override fun bind(model: ArticleResponse) {
        setItem(model)
        initClickListener(model)
    }

    private fun initClickListener(articleResponse: ArticleResponse) {
        setOnClickListener {
            itemClickSubject.onNext(articleResponse)
        }
    }

    // MARK: Assistant functions
    private fun setItem(model: ArticleResponse) {
        setTitle(model.title)
        setSummary(model.summary)
        setImage(model.imageUrl)
        setDate(model.publishedAt)
    }

    private fun setDate(apiDate: String?){
        val firstDate = apiDate!!.substring(0,10)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = formatter.parse(firstDate)
        val desiredFormat = SimpleDateFormat("MMM dd, yyyy",Locale.ENGLISH ).format(date!!)
        itemView.vTvTime.text = desiredFormat
    }

    private fun setImage(imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(itemView.vIvItemImage)
    }

    private fun setTitle(title: String?) {
        itemView.vTvItemTitle.text = title
    }

    private fun setSummary(summary: String?) {
        setMaxLines()
        itemView.vTvItemSummary.text = summary
    }

    private fun setMaxLines() {
        itemView.vTvItemSummary.viewTreeObserver
            .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    itemView.vTvItemSummary.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    val noOfLinesVisible: Int =
                        itemView.vTvItemSummary.height / itemView.vTvItemSummary.lineHeight
                    itemView.vTvItemSummary.maxLines = noOfLinesVisible
                    itemView.vTvItemSummary.ellipsize = TextUtils.TruncateAt.END
                }
            })
    }
}