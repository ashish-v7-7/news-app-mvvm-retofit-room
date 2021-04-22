package com.zaidzakir.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zaidzakir.newsapp.models.Article

/**
 *Created by Zaid Zakir
 */
@Dao
interface ArticleDao {
    //If article already exist then replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}