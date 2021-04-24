package com.zaidzakir.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaidzakir.newsapp.R
import com.zaidzakir.newsapp.adapters.NewsAdapter
import com.zaidzakir.newsapp.ui.NewsActivity
import com.zaidzakir.newsapp.ui.NewsViewModel
import com.zaidzakir.newsapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

/**
 *Created by Zaid Zakir
 */
class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response->
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
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun progressBarVisibility(visible:Boolean){
        if (visible) paginationProgressBar.visibility = View.VISIBLE else paginationProgressBar.visibility = View.INVISIBLE
    }
}