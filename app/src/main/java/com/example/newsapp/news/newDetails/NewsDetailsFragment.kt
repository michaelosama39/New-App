package com.example.newsapp.news.newDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.core.utils.BusinessConst.NEWS_DATA_ITEM
import com.example.newsapp.news.newList.model.Article
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsItem : Article = arguments?.getSerializable(NEWS_DATA_ITEM) as Article
        Glide.with(requireContext()).load(newsItem.urlToImage).into(newsIV)
        tvHeadLineDetail.text = newsItem.title
    }

}