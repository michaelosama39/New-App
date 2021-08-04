package com.example.newsapp.news.newList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.news.newList.model.Article


class NewsAdapter( val newsItemListener: NewItemListener ) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var newsDataList: ArrayList<Article>? = arrayListOf()

    fun setItems(newsDataList: ArrayList<Article>?) {
        this.newsDataList = newsDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        return NewsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsDataList?.size!!
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        newsDataList?.get(position)?.let { holder.bindData(it) }
        holder.itemView.setOnClickListener {
            newsDataList?.get(position)?.let { it1 -> newsItemListener.onNewsItemClicked(it1) }
          //  holder.itemView.findNavController().navigate(R.id.from_list_to_details_action , bundleOf(NEWS_DATA_ITEM to newsDataList[position]))
        }
    }

    interface NewItemListener{
        fun onNewsItemClicked(article: Article)
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headLineTV: TextView = view.findViewById(R.id.headLineTV)
        val channelTV: TextView = view.findViewById(R.id.channelTV)
        val newsDataTV: TextView = view.findViewById(R.id.newsDataTV)
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
        fun bindData(article: Article) {
            headLineTV.text = article.title
            channelTV.text = article.content
            newsDataTV.text = article.description
            Glide.with(newsImage.context).load(article.urlToImage).into(newsImage)
        }
    }
}