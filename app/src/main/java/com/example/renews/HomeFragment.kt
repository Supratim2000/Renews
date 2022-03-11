package com.example.renews

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeFragment: Fragment(), NewsItemClick {

    private val API_KEY: String = "3947c821d3304e5296f1900043f79193"
    private lateinit var searchEt: EditText
    private lateinit var searchBt: MaterialButton
    private lateinit var homeRv: RecyclerView
    private lateinit var fetchedHomeList: ArrayList<Model>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeView: View = inflater.inflate(R.layout.fragment_home,null)
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()

        searchBt.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val searchedString: String = searchEt.text.toString()
                searchEt.text.clear()
                if(searchedString.isEmpty()) {
                    Toast.makeText(requireContext(),"Search field can't be empty",Toast.LENGTH_SHORT).show()
                } else
                {
                    val trimmedText: String = searchedString.trim()
                    homeRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    val homeAdapter: NewsRecyclerViewAdapter = NewsRecyclerViewAdapter(this@HomeFragment,requireContext(),fetchedHomeList)
                    homeRv.adapter = homeAdapter
                    val retrofitNewsClient: RetrofitNewsClient = RetrofitNewsClient()
                    val retrofitInstance: Retrofit? = retrofitNewsClient.getRetrofitClient()
                    val newsAPI = retrofitInstance?.create(NewsService::class.java)
                    newsAPI?.getNewsSearch(trimmedText, API_KEY)?.enqueue(object: Callback<News> {
                        override fun onResponse(call: Call<News>, response: Response<News>) {
                            if(response.isSuccessful) {
                                val data: News? = response.body()
                                if(data!=null) {
                                    fetchedHomeList.clear()
                                    for(eachNews in data.getArticles()) {
                                        fetchedHomeList.add(eachNews)
                                    }
                                    homeAdapter.notifyDataSetChanged()
                                }
                            } else {
                                Toast.makeText(activity,"Loading failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<News>, t: Throwable) {
                            Toast.makeText(activity,"Failed to load news in Home", Toast.LENGTH_SHORT).show()
                            t.printStackTrace()
                        }
                    })
                }
            }
        })

    }

    private fun initVariables() {
        searchEt = view?.findViewById(R.id.searchEt)!!
        searchBt = view?.findViewById(R.id.searchBt)!!
        homeRv = view?.findViewById(R.id.homeRv)!!
        fetchedHomeList = ArrayList()
    }

    override fun onClick(newsItem: Model) {
        val sendIntent: Intent = Intent(activity, NewsView::class.java)
        sendIntent.putExtra("url",newsItem.getUrl())
        startActivity(sendIntent)
    }
}