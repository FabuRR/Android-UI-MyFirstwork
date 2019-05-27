package com.example.firstwork;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SearchActivity extends AppCompatActivity {

    private Button back;
    private Button search;
    private ProgressBar progressBar;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        content = (EditText) findViewById(R.id.search_content);

        progressBar = (ProgressBar) findViewById(R.id.search_progress);
        progressBar.setVisibility(View.GONE);
        back = (Button) findViewById(R.id.back_button_search);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        search = (Button) findViewById(R.id.search_func);
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    //progressBar.setVisibility(View.VISIBLE);
                String input = content.getText().toString();
                if(!input.equals("")){
                    Toast.makeText(SearchActivity.this, "正在搜索"+input, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(SearchActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
