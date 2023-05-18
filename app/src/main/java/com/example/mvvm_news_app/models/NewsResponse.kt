package com.example.mvvm_news_app.models

import com.example.mvvm_news_app.models.Article


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)