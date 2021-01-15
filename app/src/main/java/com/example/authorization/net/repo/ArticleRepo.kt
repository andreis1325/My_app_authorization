package com.example.authorization.net.repo

import com.example.authorization.net.responses.Article
import io.reactivex.Observable
import com.example.authorization.net.responses.ArticleResponse

import com.example.authorization.net.services.ArticleService

class ArticleRepo(private val api: ArticleService){

    fun getArticle(): Observable<MutableList<Article>> = api.getArticles()

    fun getArticlesById(id: String): Observable<ArticleResponse> = api.getArticlesById(id)

    fun getArticlesLinkedByLaunch(id: String): Observable<ArticleResponse> = api.getArticlesLinkedByLaunch(id)
}