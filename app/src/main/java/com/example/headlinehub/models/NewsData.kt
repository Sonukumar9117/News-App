package com.example.headlinehub.models

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)