package com.example.firstwork;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet arrs){
        super(context, arrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button article_button = (Button) findViewById(R.id.title_article);
        Button news_button = (Button) findViewById(R.id.title_news);
        Button rem_button = (Button) findViewById(R.id.title_reminder);
        Button search_button = (Button) findViewById(R.id.title_search);


    }

}
