package com.example.firstwork;

public class Article {

    private int id;
    private int headImgId;
    private String author;
    private int mainImgId;
    private String title;
    private String content;


    public Article(int id, int headImgId, String author, int mainImgId, String title){
        this.id = id;
        this.headImgId = headImgId;
        this.author = author;
        this.mainImgId = mainImgId;
        this.title = title;
    }
    public Article(int headImgId, String author, int mainImgId, String title){
        this.headImgId = headImgId;
        this.author = author;
        this.mainImgId = mainImgId;
        this.title = title;
    }
    public Article(int id, int headImgId, String author, int mainImgId, String title, String content){
        this.id = id;
        this.headImgId = headImgId;
        this.author = author;
        this.mainImgId = mainImgId;
        this.title = title;
        this.content = content;
    }

    public int getId(){
        return id;
    }

    public int getHeadImgId(){
        return headImgId;
    }

    public String getAuthor(){
        return author;
    }

    public int getMainImgId(){
        return mainImgId;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
