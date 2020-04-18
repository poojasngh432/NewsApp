package com.example.newsapp;

import com.example.newsapp.activity.NewsByKeyword;
import com.example.newsapp.model.News;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {

    String BASE_URL_NEWS = "https://newsapi.org/v2/";

    String NEWS_API_KEY = "";

    // for a keyword
    // http://newsapi.org/v2/everything?q=bitcoin&sortBy=publishedAt&apiKey=69461f79c446489e8df57822f2c8f0dd

    @GET("top-headlines?country=in&apiKey="+NEWS_API_KEY)
    Call<News> getNews();

    @GET("everything")
    Call<News> getNewsByKeyword(
            @QueryMap Map<String, String> options
    );

}
