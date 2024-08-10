package com.example.headlinehub.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.headlinehub.R
import com.example.headlinehub.databinding.ActivityDashboardBinding
import com.example.headlinehub.models.Article
import com.example.headlinehub.ui.fragment.DiscoverFragment
import com.example.headlinehub.ui.fragment.HomeFrament
import com.example.headlinehub.ui.fragment.SavedMessageFragment
import com.example.headlinehub.ui.fragment.SearchFragment
import com.example.headlinehub.models.GetData
import com.example.headlinehub.models.NewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var homeFrament: HomeFrament
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //fetch data using retrofit
        val retrofit= Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create<GetData>(GetData::class.java)
        val retrofitData=retrofit.getData()
        retrofitData.enqueue(object: Callback<NewsData?>{
            override fun onResponse(p0: Call<NewsData?>, p1: Response<NewsData?>) {
                val newsData=p1.body()
                val bundle=Bundle()
                var newsArticle=newsData?.articles
                var news:ArrayList<Article> = arrayListOf()
                for( item in newsArticle!!){
                    news.add(item)
                }
                bundle.putSerializable("news",news)
                homeFrament= HomeFrament()
                homeFrament.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,homeFrament).commit()
            }

            override fun onFailure(p0: Call<NewsData?>, p1: Throwable) {
            }

        })
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                    homeFrament
                ).commit()
                R.id.discover->supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                   DiscoverFragment()
                ).commit()
                R.id.search->supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                    SearchFragment()
                ).commit()
                R.id.saved->supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                    SavedMessageFragment()
                ).commit()
        }
            true
        }
    }
}