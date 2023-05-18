package com.example.mvvm_news_app.repository

import com.example.mvvm_news_app.api.RetrofitInstance
import com.example.mvvm_news_app.db.ArticleDatabase

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}