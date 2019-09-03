package com.manuj.projectwork;

public class NewsFlash {
    public String author;
    public String title;
    public String url;
    public String urlToImage;
    public String publishedAt;


    public NewsFlash() {
    }

    public NewsFlash(String author, String title, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }



    @Override
    public String toString() {
        return "NewsFlash{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}

