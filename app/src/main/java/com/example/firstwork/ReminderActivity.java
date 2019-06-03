package com.example.firstwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firstwork.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

public class ReminderActivity extends AppCompatActivity {

    private ListView listView;

    private Button back;

    private TextView no_noti;

    List<Notification> notiList;

    List<Notification> nList_json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        HttpUtil.sendOkHttpRequest("http://10.0.2.2/notis.json", new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Random rand = new Random();
                for(int i = 0; i < 15; i++) {
                    if((rand.nextInt(100) + 1) % 10 == 0)
                        getNotiFromJson(responseData);
                }
                if(!nList_json.isEmpty()){
                    no_noti.setVisibility(View.GONE);
                }
                NotificationAdapter notiAdapter = new NotificationAdapter(ReminderActivity.this, R.layout.noti_item, nList_json);
                listView.setAdapter(notiAdapter);
                //notiAdapter.isEnabled(listView.getLastVisiblePosition());
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Notification noti = nList_json.get(position);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(ReminderActivity.this);
                        dialog.setTitle(noti.getnTitle());
                        dialog.setMessage(noti.getDetail());
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

        notiList = new ArrayList<>();
        nList_json = new ArrayList<>();
        no_noti = (TextView) findViewById(R.id.no_noti);
        Notification n1 = new Notification("Notification Title", "Message detail here Message detail here Message detail here");

        /*
        Random rand = new Random();
        for(int i = 0; i < 30; i++){
            if((rand.nextInt(100) + 1) % 10 == 0){
                notiList.add(n1);
            }
        }
        */



        listView = (ListView) findViewById(R.id.rem_list_view);
        /*
        NotificationAdapter notiAdapter = new NotificationAdapter(ReminderActivity.this, R.layout.noti_item, notiList);
        listView.setAdapter(notiAdapter);
        //notiAdapter.isEnabled(listView.getLastVisiblePosition());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notification noti = notiList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(ReminderActivity.this);
                dialog.setTitle(noti.getnTitle());
                dialog.setMessage(noti.getDetail());
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        */

        back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    private void getNotiFromJson(String jsonData){
        Gson gson = new Gson();
        List<Notification> nList = gson.fromJson(jsonData, new TypeToken<List<Notification>>(){}.getType());
        for(Notification n : nList){
            Log.d("ReminderActivity", "title is " + n.getnTitle());
            //Log.d("ReminderActivity", "preview of the message: " + n.getPreview());
            Log.d("ReminderActivity", "detail is " + n.getDetail());
            Notification no = new Notification(n.getnTitle(), n.getDetail());
            nList_json.add(no);
        }
    }
}
