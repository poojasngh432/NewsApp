package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsapp.Api;
import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.model.Article;
import com.example.newsapp.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndiaNewsActivity extends AppCompatActivity {
    private List<Article> newsdata;
    private Context mContext;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_news);
        mContext = IndiaNewsActivity.this;
        callApi();
    }

    private void callApi() {
        showLoadingDialog();
        //Creating a retrofit object

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);
        Call<News> call = api.getNews();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                dismissLoadingDialog();
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    newsdata = response.body().getArticles();
                    setRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                dismissLoadingDialog();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setRecyclerView() {
        if(newsdata != null){
            newsAdapter = new NewsAdapter(newsdata, mContext);
            recyclerView = findViewById(R.id.news_rv);
            layoutManager = new LinearLayoutManager(IndiaNewsActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            newsAdapter = new NewsAdapter(newsdata, mContext);
            recyclerView.setAdapter(newsAdapter);
            newsAdapter.notifyDataSetChanged();
            initListener();
        }
    }

    private void initListener(){

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView = view.findViewById(R.id.img);
                Intent intent = new Intent(IndiaNewsActivity.this, NewsDetailActivity.class);

                Article article = newsdata.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img",  article.getUrlToImage());
                intent.putExtra("date",  article.getPublishedAt());
                intent.putExtra("source",  article.getSource().getName());
                intent.putExtra("author",  article.getAuthor());

                startActivity(intent);
            }
        });

    }

    private void showLoadingDialog() {
        progressDialog = ProgressDialog.show(mContext, null, this.getString(R.string.loading), false, false);
    }

    private void dismissLoadingDialog() {
        try {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            progressDialog = null;
        }
    }
}
