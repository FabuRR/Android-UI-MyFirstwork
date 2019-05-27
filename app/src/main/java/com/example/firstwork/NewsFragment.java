package com.example.firstwork;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private ListView listView;

    private List<News> newsList;
    private NewsAdapter newsAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsList = new ArrayList<>();

        News n = new News("TITLE HERE", R.drawable.news_img);
        for(int i = 0; i < 50; i++){
            newsList.add(n);
        }

        newsAdapter = new NewsAdapter(getActivity(), R.layout.news_item, newsList);
        listView = (ListView) view.findViewById(R.id.news_list_view);
        listView.setAdapter(newsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
