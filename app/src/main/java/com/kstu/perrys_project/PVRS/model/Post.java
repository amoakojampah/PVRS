package com.kstu.perrys_project.PVRS.model;

public class Post {

    private String title, content, imageUrl, date, authorId;

    public Post() {
    }

    public Post(String title, String content, String imageUrl, String date, String authorId) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.authorId = authorId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
