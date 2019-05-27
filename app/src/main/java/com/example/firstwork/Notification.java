package com.example.firstwork;

public class Notification {
    private String nTitle;
    private String preview;
    private String detail;

    public Notification(String nTitle, String detail){
        this.nTitle = nTitle;
        this.detail = detail;
        preview = detail.substring(0, 35) + "...";
    }

    public String getnTitle() {
        return nTitle;
    }

    public String getPreview(){
        return preview;
    }

    public String getDetail(){
        return detail;
    }
}
