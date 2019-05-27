package com.example.firstwork;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    private int resourceId;

    public ArticleAdapter(Context context, int textViewResourceId, List<Article> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView headImage = (ImageView) view.findViewById(R.id.article_author_head);
        TextView author = (TextView) view.findViewById(R.id.article_author_name);
        ImageView mainImage = (ImageView) view.findViewById(R.id.article_main_image);
        TextView title = (TextView) view.findViewById(R.id.article_title);
        Button article_option = (Button) view.findViewById(R.id.article_option_button);
        article_option.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                PopupMenu myMenu = new PopupMenu(getContext(), v);
                myMenu.getMenuInflater().inflate(R.menu.article_option, myMenu.getMenu());
                myMenu.show();
                myMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.collect_item:
                                Toast.makeText(getContext(), "点击了收藏", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.share_item:
                                Toast.makeText(getContext(), "点击了分享", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        headImage.setImageResource(article.getHeadImgId());
        author.setText(article.getAuthor());
        mainImage.setImageResource(article.getMainImgId());
        title.setText(article.getTitle());

        return view;
    }
}
