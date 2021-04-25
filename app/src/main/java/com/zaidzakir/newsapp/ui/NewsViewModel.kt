package com.zaidzakir.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidzakir.newsapp.models.Article
import com.zaidzakir.newsapp.models.NewsResponse
import com.zaidzakir.newsapp.repository.NewsRepository
import com.zaidzakir.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 *Created by Zaid Zakir
 */
class NewsViewModel(
        val newsRepository: NewsRepository
) : ViewModel() {

    //used for our fragment to subscribe as observers and observe any changes, since in mvvm viewmodel has no relationship with ui
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse:NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse:NewsResponse? = null

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                //increase page number by 1 everytime we recieve response
                breakingNewsPage++
                //if its first response we set to result response
                if (breakingNewsResponse == null){
                    breakingNewsResponse = resultResponse
                }else{
                    //else add on top on old response
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                //increase page number by 1 everytime we recieve response
                searchNewsPage++
                //if its first response we set to result response
                if (searchNewsResponse == null){
                    searchNewsResponse = resultResponse
                }else{
                    //else add on top on old response
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article:Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

}