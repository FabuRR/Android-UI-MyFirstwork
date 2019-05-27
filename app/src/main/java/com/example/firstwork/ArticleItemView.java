package com.example.firstwork;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ArticleItemView extends LinearLayout{

    private Context context;

    public ArticleItemView(Context context, AttributeSet attr){
        super(context, attr);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.content_item, this, true);
    }


}
