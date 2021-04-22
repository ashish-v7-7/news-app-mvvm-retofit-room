package com.zaidzakir.newsapp.models

import com.zaidzakir.newsapp.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)