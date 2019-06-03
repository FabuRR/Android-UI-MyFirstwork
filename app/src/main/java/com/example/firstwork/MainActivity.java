package com.example.firstwork;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Article> articleList = new ArrayList<>();

    private Button article_button;
    private Button news_button;
    private Button reminder_button;
    private Button search_button;
    private ViewPager view_pager;
    private ArticleFragment articleFragment;
    private NewsFragment newsFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        article_button = (Button) findViewById(R.id.title_article);
        news_button = (Button) findViewById(R.id.title_news);
        reminder_button = (Button) findViewById(R.id.title_reminder);
        search_button = (Button) findViewById(R.id.title_search);



        article_button.setOnClickListener(this);
        news_button.setOnClickListener(this);
        reminder_button.setOnClickListener(this);
        search_button.setOnClickListener(this);

        view_pager = (ViewPager) findViewById(R.id.view_pager);

        articleFragment = new ArticleFragment();
        newsFragment = new NewsFragment();
        mFragmentList.add(articleFragment);
        mFragmentList.add(newsFragment);


        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        view_pager.setOffscreenPageLimit(4);
        view_pager.setAdapter(mFragmentAdapter);
        view_pager.setCurrentItem(0);
        article_button.setTextColor(Color.rgb(255, 255, 150));
        article_button.setTextSize(24);

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch(i){
                    case 0:
                        article_button.setTextColor(Color.rgb(255, 255, 150));
                        article_button.setTextSize(24);
                        news_button.setTextColor(Color.rgb(255,255,255));
                        news_button.setTextSize(20);
                        break;
                    case 1:
                        article_button.setTextColor(Color.rgb(255, 255, 255));
                        article_button.setTextSize(20);
                        news_button.setTextColor(Color.rgb(255, 255, 150));
                        news_button.setTextSize(24);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_article:
                view_pager.setCurrentItem(0, true); break;
            case R.id.title_news:
                view_pager.setCurrentItem(1, true); break;
            case R.id.title_reminder:
                Intent RemIntent = new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(RemIntent);
                break;
            case R.id.title_search:
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                break;
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList){
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position){
            return fragmentList.get(position);
        }

        @Override
        public int getCount(){
            return fragmentList.size();
        }
    }


}
