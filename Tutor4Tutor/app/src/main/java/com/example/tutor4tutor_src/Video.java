package com.example.tutor4tutor_src;


public class Video {
    //this is for list containing lecture title and its URL on Lectures Activity
    private String title;
    private String url;

    public Video (String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }

    public void setTitle (String title) {
        this.title = title;
    }
    public void setUrl (String url) {
        this.url = url;
    }
}
