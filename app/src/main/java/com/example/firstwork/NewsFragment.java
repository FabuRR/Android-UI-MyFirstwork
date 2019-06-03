package com.example.firstwork;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firstwork.util.HttpUtil;
import com.example.firstwork.util.NewsHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Response;

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

        //News n = new News("TITLE HERE", R.drawable.news_img);
        //for(int i = 0; i < 50; i++){
        //    newsList.add(n);
        //}


        listView = (ListView) view.findViewById(R.id.news_list_view);


        HttpUtil.sendOkHttpRequest("http://10.0.2.2/news.xml", new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                for(int i = 0; i < 30; i++)
                    getNewsFromXML(responseData);
                newsAdapter = new NewsAdapter(getActivity(), R.layout.news_item, newsList);
                listView.setAdapter(newsAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                        News n = newsList.get(position);

                        Intent intent = new Intent(getActivity(), NewsActivity.class);
                        intent.putExtra("news_title", n.getTitle());
                        intent.putExtra("news_img", n.getImageId());
                        intent.putExtra("news_content", n.getContent());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

        return view;
    }

    private void getNewsFromXML(String xmlData){
        try{
            /*
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            NewsHandler handler = new NewsHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
            */

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String title = "";
            String imageName = "";
            String content = "";
            while(eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("title".equals(nodeName)){
                            title = xmlPullParser.nextText();
                        }else if("imageId".equals(nodeName)){
                            imageName = xmlPullParser.nextText();
                        }else if("content".equals(nodeName)){
                            content = xmlPullParser.nextText();
                        }
                        break;
                    }

                    case XmlPullParser.END_TAG:{
                        if("news".equals(nodeName)){
                            Log.d("News", "title is " + title);
                            Log.d("News", "image name is " + imageName);
                            Log.d("News", "content is " + content);
                            int imgId = getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
                            News n = new News(title, imgId, content);
                            newsList.add(n);
                        }
                        break;
                    }

                    default: break;
                }
                eventType = xmlPullParser.next();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
