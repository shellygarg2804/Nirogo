package com.example.nirogo.Post;

public class PostUploadInfo {

String docImage, docName, docSpec, time, desc, url,useruid;

    public PostUploadInfo() {
    }

    public PostUploadInfo(String docImage, String docName, String docSpec, String time, String desc, String url,String useruid) {
        this.docImage = docImage;
        this.docName = docName;
        this.docSpec = docSpec;
        this.time = time;
        this.desc = desc;
        this.url = url;
        this.useruid= useruid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
    public void setUseruid(String useruid){
        this.useruid= useruid;
    }

    public String getUseruid() {
        return useruid;
    }

    public String getDocImage() {
        return docImage;
    }

    public void setDocImage(String docImage) {
        this.docImage = docImage;
    }
}
