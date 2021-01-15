package com.example.authorization.net.repo

import android.content.SharedPreferences
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.net.responses.BlogResponse
import com.example.authorization.net.services.BlogService
import io.reactivex.Observable

class BlogRepo(private val api: BlogService){

    fun getBlog(): Observable<List<BlogResponse>> = api.getBlogs()

    fun getArticlesById(id: String): Observable<BlogResponse> = api.getBlogById(id)

}