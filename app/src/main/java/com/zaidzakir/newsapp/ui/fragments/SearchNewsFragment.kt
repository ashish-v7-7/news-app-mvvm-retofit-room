package com.zaidzakir.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zaidzakir.newsapp.R
import com.zaidzakir.newsapp.ui.NewsActivity
import com.zaidzakir.newsapp.ui.NewsViewModel

/**
 *Created by Zaid Zakir
 */
class SearchNewsFragment:Fragment(R.layout.fragment_search_news) {
    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as NewsActivity).viewModel
    }

}