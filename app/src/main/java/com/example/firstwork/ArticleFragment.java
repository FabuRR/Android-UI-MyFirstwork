package com.example.firstwork;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private ListView listView;
    private LinearLayout linearLayout;

    private ArticleAdapter articleAdapter;
    private List<Article> articleList;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        articleList = new ArrayList<>();

        //Article a = new Article(R.drawable.head_img, "AUTHOR", R.drawable.main_img, "TITLE HERE");
        for(int i = 0; i < 50; i++){
            Article article = new Article(i + 1, R.drawable.head_img, "AUTHOR", R.drawable.main_img, "TITLE HERE");
            articleList.add(article);
        }
        listView = (ListView) view.findViewById(R.id.article_list_view);
        articleAdapter = new ArticleAdapter(getActivity(), R.layout.content_item, articleList);
        listView.setAdapter(articleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = articleList.get(position);
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("article_id", article.getId());
                startActivity(intent);
            }
        });

        return view;
    }

}
