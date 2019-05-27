package com.example.firstwork;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReminderActivity extends AppCompatActivity {

    private Button back;

    private TextView no_noti;

    List<Notification> notiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        notiList = new ArrayList<>();
        no_noti = (TextView) findViewById(R.id.no_noti);
        Notification n1 = new Notification("Notification Title", "Message detail here Message detail here Message detail here");

        Random rand = new Random();
        for(int i = 0; i < 30; i++){
            if((rand.nextInt(100) + 1) % 10 == 0){
                notiList.add(n1);
            }
        }

        if(!notiList.isEmpty()){
            no_noti.setVisibility(View.GONE);
        }

        ListView listView = (ListView) findViewById(R.id.rem_list_view);
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

        back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
