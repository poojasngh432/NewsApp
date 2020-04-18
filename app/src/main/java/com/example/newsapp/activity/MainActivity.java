package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.newsapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView IVIndiaNews, IVWorldNews, IVSearchNews, IVLatestNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IVIndiaNews = (ImageView) findViewById(R.id.iv_india_news);
        IVWorldNews = (ImageView) findViewById(R.id.iv_world_news);
        IVSearchNews = (ImageView) findViewById(R.id.iv_search_news);
        IVLatestNews = (ImageView) findViewById(R.id.iv_latest_news);

        IVIndiaNews.setOnClickListener(this);
        IVWorldNews.setOnClickListener(this);
        IVSearchNews.setOnClickListener(this);
        IVLatestNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_india_news:
                showIndiaNews();
                break;

            case R.id.iv_world_news:
                showWorldNews();
                break;

            case R.id.iv_search_news:
                showNewsByKeyWordSearch();
                break;
            case R.id.iv_latest_news:
                showLatestNews();
                break;
        }

    }

    private void showLatestNews() {
        Intent intent = new Intent(this, IndiaNewsActivity.class);
        startActivity(intent);
    }

    private void showNewsByKeyWordSearch() {
        Intent intent = new Intent(this, NewsByKeyword.class);
        startActivity(intent);
    }

    private void showWorldNews() {
        Intent intent = new Intent(this, IndiaNewsActivity.class);
        startActivity(intent);
    }

    private void showIndiaNews() {
        Intent intent = new Intent(this, IndiaNewsActivity.class);
        startActivity(intent);
    }
}
