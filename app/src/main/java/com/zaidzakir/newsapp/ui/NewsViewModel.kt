package com.zaidzakir.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}