package com.example.renews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class NewsView : AppCompatActivity() {
    private lateinit var currentNewsTb: Toolbar
    private lateinit var showNewsWv: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_view)
        initVariables()
        //setSupportActionBar(currentNewsTb)
        val fetchedIntent: Intent = intent
        val newsUrl: String? = fetchedIntent.getStringExtra("url")
        if(!newsUrl.isNullOrEmpty()) {
            showNewsWv.webViewClient = WebViewClient()
            showNewsWv.loadUrl(newsUrl)
        } else {
            Toast.makeText(this,"Invalid news link",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initVariables() {
        currentNewsTb = findViewById(R.id.currentNewsTb)
        showNewsWv = findViewById(R.id.showNewsWv)
    }
}