package com.example.renews

class News {
    private val status: String
    private val totalResults: Int
    private val articles: ArrayList<Model>

    constructor(status: String, totalResults: Int, articles: ArrayList<Model>) {
        this.status = status
        this.totalResults = totalResults
        this.articles = articles
    }

    fun getStatus() = this.status
    fun getTotalResults() = this.totalResults
    fun getArticles() = this.articles
}