package com.example.mvvm_news_app.repository

import com.example.mvvm_news_app.api.RetrofitInstance
import com.example.mvvm_news_app.db.ArticleDatabase
import com.example.mvvm_news_app.models.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteNews(article: Article) = db.getArticleDao().deleteArticle(article)

}