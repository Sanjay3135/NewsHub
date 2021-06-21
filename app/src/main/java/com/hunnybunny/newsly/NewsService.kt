package com.hunnybunny.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=apple&from=2021-06-05&to=2021-06-05&sortBy=popularity&apiKey=00eae29c59f549a4a41ff8cfe158c5a2
//https://newsapi.org/v2/top-headlines?country=in&apiKey=00eae29c59f549a4a41ff8cfe158c5a2


//https://newsapi.org/v2/everything?q=tesla&from=2021-05-07&sortBy=publishedAt&apiKey=00eae29c59f549a4a41ff8cfe158c5a2

//https://newsapi.org/v2/everything?q=apple&from=2021-06-06&to=2021-06-06&sortBy=popularity&apiKey=00eae29c59f549a4a41ff8cfe158c5a2

//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=00eae29c59f549a4a41ff8cfe158c5a2

const val BASE_URL="https://newsapi.org/"
const val API_KEY="00eae29c59f549a4a41ff8cfe158c5a2"
interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country")country:String,@Query("page")page:Int):Call<News>

    @GET("v2/top-headlines?sources=techcrunch&apiKey=00eae29c59f549a4a41ff8cfe158c5a2")
    fun getTesla(@Query("page")page: Int):Call<News>

    @GET("v2/everything?q=apple&from=2021-06-06&to=2021-06-06&sortBy=popularity&apiKey=$API_KEY")
    fun getApple(@Query("page")page:Int):Call<News>
}
object NewsService{
    val newsInstance:NewsInterface
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}