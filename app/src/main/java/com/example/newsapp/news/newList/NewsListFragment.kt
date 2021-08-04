package com.example.newsapp.news.newList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.newsapp.R
import com.example.newsapp.core.utils.BusinessConst.NEWS_DATA_ITEM
import com.example.newsapp.core.utils.Resource
import com.example.newsapp.news.newList.model.Article
import com.example.newsapp.news.newList.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_list.*

@AndroidEntryPoint
class NewsListFragment : Fragment(), NewsAdapter.NewItemListener {

    private val viewModel: NewsViewModel by viewModels()

    lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.e("Status" , "Success" + it.data+"")
                    adapter.setItems(it.data as ArrayList<Article>?)
                }
                Resource.Status.ERROR -> {
                    Log.e("Status" , "error" + it.message +"")
                }
                Resource.Status.LOADING -> {
                    Log.e("Status" , "loading")
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(this)
        newsRv.adapter = adapter
        newsRv.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
    }


    override fun onNewsItemClicked(article: Article) {
        findNavController().navigate(
            R.id.from_list_to_details_action,
            bundleOf(NEWS_DATA_ITEM to article)
        )
    }
}