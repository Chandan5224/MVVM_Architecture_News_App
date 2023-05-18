package com.example.mvvm_news_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_news_app.R
import com.example.mvvm_news_app.models.Article
import org.w3c.dom.Text

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleBViewHolder>() {
    inner class ArticleBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleImage: ImageView = itemView.findViewById(R.id.ivArticleImage)
        val articleTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val articleDes: TextView = itemView.findViewById(R.id.tvDescription)
        val articleSource: TextView = itemView.findViewById(R.id.tvSource)
        val articleDate: TextView = itemView.findViewById(R.id.tvPublishedAt)
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleBViewHolder {
        return ArticleBViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleBViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.articleImage)
            holder.articleSource.text = article.source.name
            holder.articleDate.text = article.publishedAt
            holder.articleDes.text = article.description
            holder.articleTitle.text = article.title
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}