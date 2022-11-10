package com.kstu.perrys_project.PVRS;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String newsContent;
    String imageUrl;
    TextView mNewsContent;
    ImageView mNewsHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        newsContent = getIntent().getStringExtra("newsContent");
        imageUrl = getIntent().getStringExtra("imageUrl");

        mNewsContent = findViewById(R.id.TV_news_content);
        mNewsHeader = findViewById(R.id.IV_news_image_header);

        mNewsContent.setText(newsContent);
        Picasso.with(NewsDetailActivity.this)
                .load(imageUrl)
                .into(mNewsHeader);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
