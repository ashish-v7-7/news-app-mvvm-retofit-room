package com.zaidzakir.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zaidzakir.newsapp.repository.NewsRepository

/**
 *Created by Zaid Zakir
 */
class NewsViewModelProviderFactory(
        val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}