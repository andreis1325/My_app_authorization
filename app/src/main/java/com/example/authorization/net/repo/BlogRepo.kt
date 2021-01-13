package com.example.authorization.net.repo

import android.content.SharedPreferences
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.net.responses.BlogResponse
import com.example.authorization.net.services.BlogService
import io.reactivex.Observable

class BlogRepo(private val api: BlogService, private val sharedPreferences: SharedPreferences){

    fun getBlog(): Observable<BlogResponse> = api.getBlogs()

    fun getArticlesById(id: String): Observable<BlogResponse> = api.getBlogById(id)

}