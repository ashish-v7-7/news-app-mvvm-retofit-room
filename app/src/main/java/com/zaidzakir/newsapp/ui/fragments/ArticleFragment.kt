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
class ArticleFragment:Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity) as NewsViewModel
    }
}