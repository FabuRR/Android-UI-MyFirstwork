package com.example.firstwork;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private Button option;
    private TextView title;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        init();

        back.setOnClickListener(this);
        option.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        if(!url.contains("://")){
            url = "https://" + url;
        }
        title.setText(url);
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in_content_back:
                finish();
                break;
                default:
                    break;
        }
    }

    private void init(){
        back = (Button) findViewById(R.id.in_content_back);
        option = (Button) findViewById(R.id.in_content_option);
        title = (TextView) findViewById(R.id.content_category);
        webView = (WebView) findViewById(R.id.web_view);
    }
}
