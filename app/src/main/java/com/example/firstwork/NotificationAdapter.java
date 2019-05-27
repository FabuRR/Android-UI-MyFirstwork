package com.example.firstwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    private int resourceId;

    public NotificationAdapter(Context context, int textViewResourceId, List<Notification> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notification notification = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView notiTitle = (TextView) view.findViewById(R.id.noti_title);
        TextView notiPrev = (TextView) view.findViewById(R.id.noti_prev);
        notiTitle.setText(notification.getnTitle());
        notiPrev.setText(notification.getPreview());
        return view;
    }

}
