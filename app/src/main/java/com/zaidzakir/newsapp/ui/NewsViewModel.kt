package com.zaidzakir.newsapp.ui

import androidx.lifecycle.ViewModel
import com.zaidzakir.newsapp.repository.NewsRepository

/**
 *Created by Zaid Zakir
 */
class NewsViewModel(
        val newsRepository: NewsRepository
):ViewModel() {

}