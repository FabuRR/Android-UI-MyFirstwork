package com.example.firstwork.util;

import android.util.Log;

import com.example.firstwork.Article;
import com.example.firstwork.ArticleActivity;
import com.example.firstwork.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    public static String sendHttpRequest(String address){
        HttpURLConnection connection = null;
        try{
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }finally {
            if(connection != null){
                connection.disconnect();
            }
        }
    }

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /*
    public static void parseJSONWithJSONObject(String jsonData) {
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */





}
