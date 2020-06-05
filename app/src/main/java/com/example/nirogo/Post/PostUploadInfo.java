package com.example.nirogo.Post;

public class PostUploadInfo {
    String id;
    String postdetails;
    String uniqueId;

    public PostUploadInfo(String id, String postdetails, String uniqueId) {
        this.id = id;
        this.postdetails = postdetails;
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostdetails() {
        return postdetails;
    }

    public void setPostdetails(String postdetails) {
        this.postdetails = postdetails;
    }
}
