package com.example.firstwork.util;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NewsHandler extends DefaultHandler {
    private String nodeName;

    private StringBuilder title;

    private StringBuilder imageName;

    private StringBuilder content;

    @Override
    public void startDocument() throws SAXException {
        title = new StringBuilder();
        imageName = new StringBuilder();
        content = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if("title".equals(nodeName)){
            title.append(ch, start, length);
        }else if("imageId".equals(nodeName)){
            imageName.append(ch, start, length);
        }else if("content".equals(nodeName)){
            content.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("news".equals(localName)){
            Log.d("NewsHandler", "title is " + title.toString().trim());
            Log.d("NewsHandler", "image name is " + imageName.toString().trim());
            Log.d("NewsHandler", "content is " + content.toString().trim());
            title.setLength(0);
            imageName.setLength(0);
            content.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
