package com.example.firstwork;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView category;
    private Button back;
    private Button option;

    private TextView title;
    private TextView author;
    private TextView content_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initWidgets();
        category.setText("资讯");
        title.setText("TITLE");
        author.setText("AUTHOR");
        content_text.setText("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.in_content_back:
                finish();
                break;
            case R.id.in_content_option:
                PopupMenu myMenu = new PopupMenu(NewsActivity.this, v);
                myMenu.getMenuInflater().inflate(R.menu.article_option, myMenu.getMenu());
                myMenu.show();
                myMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.collect_item:
                                Toast.makeText(NewsActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.share_item:
                                Toast.makeText(NewsActivity.this, "点击了分享", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                break;
        }
    }

    private void initWidgets(){
        category = (TextView)findViewById(R.id.content_category);
        back = (Button) findViewById(R.id.in_content_back);
        option = (Button) findViewById(R.id.in_content_option);

        title = (TextView) findViewById(R.id.in_news_title);
        author = (TextView) findViewById(R.id.in_news_author);
        content_text = (TextView) findViewById(R.id.in_news_content);

        back.setOnClickListener(this);
        option.setOnClickListener(this);
    }
}
