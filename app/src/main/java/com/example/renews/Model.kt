package com.example.renews

class Model {
    private var author: String
    private var title: String
    private var description: String
    private var url: String
    private var urlToImage: String
    private var publishedAt: String
    private var content: String

    constructor(
        author: String,
        title: String,
        description: String,
        url: String,
        urlToImage: String,
        publishedAt: String,
        content: String
    ) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

    fun setAuthor(author: String){
        this.author = author
    }
    fun setTitle(title: String){
        this.title = title
    }
    fun setDescription(description: String){
        this.description = description
    }
    fun setUrl(url: String){
        this.url = description
    }
    fun setUrlToImage(urlToImage: String){
        this.urlToImage = urlToImage
    }
    fun setPublishedAt(publishedAt: String) {
        this.publishedAt = publishedAt
    }
    fun setContent(content: String) {
            this.content = content
    }


    fun getAuthor() = this.author
    fun getTitle() = this.title
    fun getDescription() = this.description
    fun getUrl() = this.url
    fun getUrlToImage() = this.urlToImage
    fun getPublishedAt() = this.publishedAt
    fun getContent() = this.content
}