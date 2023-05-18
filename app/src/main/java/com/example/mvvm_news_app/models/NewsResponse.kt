package com.example.mvvm_news_app.models

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_news_app.models.Article


data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)