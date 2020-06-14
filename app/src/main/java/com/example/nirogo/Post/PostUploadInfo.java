package com.example.nirogo.Post;

public class PostUploadInfo {

String docImageurl, docName, docSpec, time, desc, posturl;
int likes;

    public PostUploadInfo() {
    }

    public PostUploadInfo(String docimage, String docName, String docSpec, String time, String desc, String url, int likes) {
        this.docImageurl = docimage;
        this.docName = docName;
        this.docSpec = docSpec;
        this.time = time;
        this.desc = desc;
        this.posturl = url;
        this.likes = likes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDocImage() {
        return docImageurl;
    }

    public void setDocImage(String docImage) {
        this.docImageurl = docImage;
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
        return posturl;
    }

    public void setUrl(String url) {
        this.posturl = url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
