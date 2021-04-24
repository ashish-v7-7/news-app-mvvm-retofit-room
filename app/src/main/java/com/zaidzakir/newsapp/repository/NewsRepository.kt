package com.zaidzakir.newsapp.repository

import com.zaidzakir.newsapp.api.RetrofitInstance
import com.zaidzakir.newsapp.api.RetrofitInstance.Companion.api
import com.zaidzakir.newsapp.database.ArticleDatabase
import retrofit2.Retrofit

/**
 *Created by Zaid Zakir
 */
class NewsRepository(
    val db: ArticleDatabase
){

    suspend fun getBreakingNews(countryCode:String, pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

}