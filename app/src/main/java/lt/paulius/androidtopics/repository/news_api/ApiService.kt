package lt.paulius.androidtopics.repository.news_api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "8c9353d4d98a44c1bde19a51f9d27171"
    ): Response<TopHeadlinesResponse>
}
