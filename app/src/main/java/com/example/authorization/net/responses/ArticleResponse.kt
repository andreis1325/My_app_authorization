package com.example.authorization.net.responses

data class ArticleResponse (
var id: String? = null,
var featured: Boolean? = null,
var title: String? = null,
var url:String? = null,
var imageUrl: String? = null,
var newsSite: String? = null,
var summary: String? = null,
var publishedAt: String? = null,
var launches: List<Provider>? = null,
var events: List<Provider>? = null)

data class Provider(
    var id: String? = null,
    var provider: String? = null
)
