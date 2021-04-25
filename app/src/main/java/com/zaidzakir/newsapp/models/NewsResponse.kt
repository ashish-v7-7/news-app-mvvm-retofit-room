package com.zaidzakir.newsapp.models

import com.zaidzakir.newsapp.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)