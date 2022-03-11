package com.example.renews

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TechnologyFragment: Fragment(), NewsItemClick {

    private val API_KEY: String = "3947c821d3304e5296f1900043f79193"
    private lateinit var technologyRv: RecyclerView
    private lateinit var fetchedTechnologyList: ArrayList<Model>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val technologyView: View = inflater.inflate(R.layout.fragment_technology,null)
        return technologyView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        technologyRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val technologyAdapter: NewsRecyclerViewAdapter = NewsRecyclerViewAdapter(this,requireContext(),fetchedTechnologyList)
        technologyRv.adapter = technologyAdapter
        val retrofitNewsClient: RetrofitNewsClient = RetrofitNewsClient()
        val retrofitInstance: Retrofit? = retrofitNewsClient.getRetrofitClient()
        val newsAPI = retrofitInstance?.create(NewsService::class.java)
        newsAPI?.getNews("in","technology",100,API_KEY)?.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful) {
                    val data: News? = response.body()
                    if(data!=null) {
                        fetchedTechnologyList.clear()
                        for(eachNews in data.getArticles()) {
                            fetchedTechnologyList.add(eachNews)
                        }
                        technologyAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(activity,"Loading failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(activity,"Failed to load news in technology category", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    private fun initVariables() {
        technologyRv = view?.findViewById(R.id.technologyRv)!!
        fetchedTechnologyList = ArrayList()
    }

    override fun onClick(newsItem: Model) {
        val sendIntent: Intent = Intent(activity, NewsView::class.java)
        sendIntent.putExtra("url",newsItem.getUrl())
        startActivity(sendIntent)
    }
}