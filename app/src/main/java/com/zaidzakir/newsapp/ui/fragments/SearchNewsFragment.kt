package com.zaidzakir.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaidzakir.newsapp.R
import com.zaidzakir.newsapp.adapters.NewsAdapter
import com.zaidzakir.newsapp.ui.NewsActivity
import com.zaidzakir.newsapp.ui.NewsViewModel
import com.zaidzakir.newsapp.utils.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.zaidzakir.newsapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *Created by Zaid Zakir
 */
class SearchNewsFragment:Fragment(R.layout.fragment_search_news) {
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        var job: Job? = null
        etSearch.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        newsViewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        newsViewModel.searchNews.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success->{
                    progressBarVisibility(false)
                    response.data?.let {newsResponse->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    progressBarVisibility(false)
                }
                is Resource.Loading->{
                    progressBarVisibility(true)
                }

            }
        })
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun progressBarVisibility(visible:Boolean){
        if (visible) paginationProgressBar.visibility = View.VISIBLE else paginationProgressBar.visibility = View.INVISIBLE
    }

}