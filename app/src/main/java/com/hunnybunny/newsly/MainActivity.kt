package com.hunnybunny.newsly

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Articles>()

    var totoalresult=-1
    var pagenum=1
    val typeofnews= arrayOf("World","Technology","Smartphone")
    var spinnertext:String=""
    lateinit var news:Call<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,typeofnews)
        spinner.adapter=arrayAdapter
        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
               spinnertext= typeofnews[position]
               if(spinnertext=="World")
               {
                   getNews(spinnertext)
               }
                else if(spinnertext=="Technology")
               {
                    getNews(spinnertext)
               }
                else if(spinnertext=="Smartphone")
               {
                   getNews(spinnertext)
               }
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        adapter=NewsAdapter(this@MainActivity, articles )
        newslist.adapter=adapter
        newslist.layoutManager=LinearLayoutManager(this)
           val manager= StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)
        newslist.layoutManager=manager
        getNews(spinnertext)

    }


    private fun getNews(type:String) {

        articles.clear()
        adapter.notifyDataSetChanged()
        Log.d("typebews",type)

        if(type=="World")
        {
             news=NewsService.newsInstance.getHeadline("in",pagenum)
        }
        else if(type=="Technology")
        {
             news=NewsService.newsInstance.getTesla(1)
        }
        else if(type=="Smartphone")
        {
            news=NewsService.newsInstance.getApple(1)
        }
        else
        {
            news=NewsService.newsInstance.getHeadline("in",pagenum)
        }
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news=response.body()
                if(news!=null)
                {

                    totoalresult=news.totalResults
                    Log.d("tota",totoalresult.toString())

                  articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failll",Toast.LENGTH_LONG).show()
            }
        })

    }
}