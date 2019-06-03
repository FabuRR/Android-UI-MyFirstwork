package com.example.firstwork;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firstwork.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private ListView listView;
    private LinearLayout linearLayout;

    private ArticleAdapter articleAdapter;
    private List<Article> articleList;

    private List<Article> aList_json;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        articleList = new ArrayList<>();
        aList_json = new ArrayList<>();
        //Article a = new Article(R.drawable.head_img, "AUTHOR", R.drawable.main_img, "TITLE HERE");
        for(int i = 0; i < 50; i++){
            Article article = new Article(i + 1, R.drawable.head_img, "AUTHOR", R.drawable.main_img, "TITLE HERE");
            articleList.add(article);
        }

        HttpUtil.sendOkHttpRequest("http://10.0.2.2/article.json", new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                //HttpUtil.parseJSONWithJSONObject(responseData);
                for(int i = 0; i < 20 ; i++)
                    getArticleFromJson(responseData);
                articleAdapter = new ArticleAdapter(getActivity(), R.layout.content_item, aList_json);
                listView.setAdapter(articleAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Article article = aList_json.get(position);
                        Intent intent = new Intent(getActivity(), ArticleActivity.class);
                        intent.putExtra("article_id", article.getId());
                        intent.putExtra("article_head", article.getHeadImgId());
                        intent.putExtra("article_author", article.getAuthor());
                        intent.putExtra("article_main", article.getMainImgId());
                        intent.putExtra("article_title", article.getTitle());
                        intent.putExtra("article_content", article.getContent());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

        listView = (ListView) view.findViewById(R.id.article_list_view);


        return view;
    }

    private void getArticleFromJson(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            //List<Article> aList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String headImgId = jsonObject.getString("headImgId");
                String author = jsonObject.getString("author");
                String mainImgId = jsonObject.getString("mainImgId");
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                Log.d("ArticleActivity", "id is " + id);
                Log.d("ArticleActivity", "head image id is " + headImgId);
                Log.d("ArticleActivity", "author is " + author);
                Log.d("ArticleActivity", "main image id is " + mainImgId);
                Log.d("ArticleActivity", "title is " + title);
                Log.d("ArticleActivity", "content contains: " + content.substring(0, 30) + "...");
                int head_id = getResources().getIdentifier(headImgId, "drawable", getContext().getPackageName());
                int main_id = getResources().getIdentifier(mainImgId, "drawable", getContext().getPackageName());
                Article a = new Article(id, head_id, author, main_id, title, content);
                aList_json.add(a);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
