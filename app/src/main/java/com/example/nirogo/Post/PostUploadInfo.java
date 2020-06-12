package com.example.nirogo.Post;

public class PostUploadInfo {

String docImage, docName, docSpec, time, desc, url;
int likes;

    public PostUploadInfo() {
    }

    public PostUploadInfo(String docName, String docSpec, String time, String desc, String url, int likes) {
        this.docName = docName;
        this.docSpec = docSpec;
        this.time = time;
        this.desc = desc;
        this.url = url;
        this.likes = likes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDocImage() {
        return docImage;
    }

    public void setDocImage(String docImage) {
        this.docImage = docImage;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSpec() {
        return docSpec;
    }

    public void setDocSpec(String docSpec) {
        this.docSpec = docSpec;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
