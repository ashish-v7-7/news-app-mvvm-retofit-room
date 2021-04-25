package com.zaidzakir.newsapp.repository

import com.zaidzakir.newsapp.api.RetrofitInstance
import com.zaidzakir.newsapp.api.RetrofitInstance.Companion.api
import com.zaidzakir.newsapp.database.ArticleDatabase
import com.zaidzakir.newsapp.models.Article
import retrofit2.Retrofit

/**
 *Created by Zaid Zakir
 */
class NewsRepository(
    val db: ArticleDatabase
){

    suspend fun getBreakingNews(countryCode:String, pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber: Int) =
            RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article:Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}