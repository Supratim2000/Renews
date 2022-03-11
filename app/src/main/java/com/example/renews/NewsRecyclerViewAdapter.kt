package com.example.renews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsRecyclerViewAdapter: RecyclerView.Adapter<NewsViewHolder> {
    private val listener: NewsItemClick
    private val context: Context
    private val newsList: ArrayList<Model>

    constructor(listener: NewsItemClick, context: Context, newsList: ArrayList<Model>) : super() {
        this.listener = listener
        this.context = context
        this.newsList = newsList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsItemView: View = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false)
        val newsItemViewHolder: NewsViewHolder = NewsViewHolder(newsItemView)
        newsItemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onClick(newsList[newsItemViewHolder.adapterPosition])
            }
        })
        return newsItemViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews :Model = newsList[position]
        val currentHeadline: String = currentNews.getTitle()
        val currentAuthor: String = currentNews.getAuthor()
        val currentPublishedAt: String = currentNews.getPublishedAt()
        val currentNewsUrl: String = currentNews.getUrl()
        val currentNewsImageUrl: String = currentNews.getUrlToImage()
        holder.titleTv.text = currentHeadline
        holder.authorTv.text = currentAuthor
        holder.publishedAtTv.text = currentPublishedAt
        try {
            Picasso.get().load(currentNewsImageUrl).placeholder(R.drawable.loading).error(R.drawable.error).into(holder.newsItemImage)
        } catch(e: Exception) {
            e.printStackTrace()
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = newsList.size
}

class NewsViewHolder(newsItemView: View) : RecyclerView.ViewHolder(newsItemView) {
    val newsItemImage: ImageView = newsItemView.findViewById(R.id.newsItemImage)
    val titleTv: TextView = newsItemView.findViewById(R.id.titleTv)
    val authorTv: TextView = newsItemView.findViewById(R.id.authorTv)
    val publishedAtTv: TextView = newsItemView.findViewById(R.id.publishedAtTv)
}