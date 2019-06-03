package com.example.firstwork;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstwork.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private Button option;

    private ImageView topImg;
    private TextView title;
    private ImageView head;
    private TextView author;
    private TextView content_text;

    private TextView category;

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.in_content_back:
                finish();
                break;
            case R.id.in_content_option:
                PopupMenu myMenu = new PopupMenu(ArticleActivity.this, v);
                myMenu.getMenuInflater().inflate(R.menu.article_option, myMenu.getMenu());
                myMenu.show();
                myMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.collect_item:
                                Toast.makeText(ArticleActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.share_item:
                                Toast.makeText(ArticleActivity.this, "点击了分享", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initWidgets();

        category.setText("文章");
        /*
            实际使用时在活动列表被点击时获取文章的id，然后从数据库中读取对应内容
        */
        Intent intent = getIntent();
        int article_id = intent.getIntExtra("article_id", -1);
        int head_id = intent.getIntExtra("article_head", -1);
        String auth = intent.getStringExtra("article_author");
        int main_id = intent.getIntExtra("article_main", -1);
        String Title = intent.getStringExtra("article_title");
        String content = intent.getStringExtra("article_content");

        //String content = "Android是一种基于Linux的自由及开放源代码的操作系统。主要使用于移动设备，如智能手机和平板电脑，由Google（谷歌）公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。Android操作系统最初由Andy Rubin开发，主要支持手机。2005年8月由Google收购注资。2007年11月，Google与84家硬件制造商、软件开发商及电信营运商组建开放手机联盟共同研发改良Android系统。随后Google以Apache开源许可证的授权方式，发布了Android的源代码。第一部Android智能手机发布于2008年10月。Android逐渐扩展到平板电脑及其他领域上，如电视、数码相机、游戏机、智能手表等。2011年第一季度，Android在全球的市场份额首次超过塞班系统，跃居全球第一。 2013年的第四季度，Android平台手机的全球市场份额已经达到78.1%。 [1]  2013年09月24日谷歌开发的操作系统Android在迎来了5岁生日，全世界采用这款系统的设备数量已经达到10亿台。\n" +
        //        "2014第一季度Android平台已占所有移动广告流量来源的42.8%，首度超越iOS。但运营收入不及iOS。";

        Article article = new Article(article_id, head_id, auth, main_id, Title, content);
        topImg.setImageResource(article.getMainImgId());
        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        head.setImageResource(article.getHeadImgId());
        content_text.setText(article.getContent());
    }

    private void initWidgets(){
        back = (Button) findViewById(R.id.in_content_back);
        option = (Button) findViewById(R.id.in_content_option);
        topImg = (ImageView) findViewById(R.id.in_article_img);
        title = (TextView) findViewById(R.id.in_article_title);
        head = (ImageView) findViewById(R.id.in_article_head);
        author = (TextView) findViewById(R.id.in_article_author);
        content_text = (TextView) findViewById(R.id.in_article_content);

        category = (TextView) findViewById(R.id.content_category);

        back.setOnClickListener(this);
        option.setOnClickListener(this);
    }
}
